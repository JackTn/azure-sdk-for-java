// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.jedis.implementation.authentication;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.azure.core.credential.TokenRequestContext;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A token cache that supports caching a token and refreshing it.
 */
public final class AccessTokenCache {

    // The delay after a refresh to attempt another token refresh
    private static final Duration REFRESH_DELAY = Duration.ofSeconds(30);
    // the offset before token expiry to attempt proactive token refresh
    private static final Duration REFRESH_OFFSET = Duration.ofMinutes(5);
    private final AtomicReference<Sinks.One<AccessToken>> wip;
    private volatile AccessToken cache;
    private volatile OffsetDateTime nextTokenRefresh = OffsetDateTime.now();
    private final TokenCredential tokenCredential;
    // Stores the last authenticated token request context. The cached token is valid under this context.
    private TokenRequestContext tokenRequestContext;
    private final Predicate<AccessToken> shouldRefresh;
    private final ClientLogger logger = new ClientLogger(AccessTokenCache.class);

    /**
     * Creates an instance of RefreshableTokenCredential with default scheme "Bearer".
     *
     * @param tokenCredential The {@link TokenCredential} to use during refreshes.
     */
    public AccessTokenCache(TokenCredential tokenCredential) {
        Objects.requireNonNull(tokenCredential, "The token credential cannot be null");
        this.wip = new AtomicReference<>();
        this.tokenCredential = tokenCredential;
        this.shouldRefresh = accessToken -> OffsetDateTime.now()
                .isAfter(accessToken.getExpiresAt().minus(REFRESH_OFFSET));
    }

    /**
     * Asynchronously get a token from either the cache or replenish the cache with a new token.
     *
     * @param tokenRequestContext The request context for token acquisition.
     * @param checkToForceFetchToken Whether token fetch should be forced.
     * @return The Publisher that emits an AccessToken
     */
    public Mono<AccessTokenResult> getToken(TokenRequestContext tokenRequestContext, boolean checkToForceFetchToken) {
        return Mono.defer(retrieveToken(tokenRequestContext, checkToForceFetchToken))
                // Keep resubscribing as long as Mono.defer [token acquisition] emits empty().
                .repeatWhenEmpty((Flux<Long> longFlux) -> longFlux.concatMap(ignored -> Flux.just(true)));
    }

    private Supplier<Mono<? extends AccessTokenResult>> retrieveToken(TokenRequestContext tokenRequestContext,
                                                                boolean checkToForceFetchToken) {
        return () -> {
            try {
                if (tokenRequestContext == null) {
                    return Mono.error(logger.logExceptionAsError(
                            new IllegalArgumentException("The token request context input cannot be null.")));
                }
                if (wip.compareAndSet(null, Sinks.one())) {
                    final Sinks.One<AccessToken> sinksOne = wip.get();
                    OffsetDateTime now = OffsetDateTime.now();
                    Mono<AccessToken> tokenRefresh;
                    Mono<AccessTokenResult> fallback;

                    // Check if the incoming token request context is different from the cached one. A different
                    // token request context, requires to fetch a new token as the cached one won't work for the
                    // passed in token request context.
                    boolean forceRefresh = (checkToForceFetchToken) || this.tokenRequestContext == null;

                    Supplier<Mono<AccessToken>> tokenSupplier = () ->
                            tokenCredential.getToken(this.tokenRequestContext);

                    if (forceRefresh) {
                        this.tokenRequestContext = tokenRequestContext;
                        tokenRefresh = Mono.defer(() -> tokenCredential.getToken(this.tokenRequestContext));
                        fallback = Mono.empty();
                    } else if (cache != null && !shouldRefresh.test(cache)) {
                        // fresh cache & no need to refresh
                        tokenRefresh = Mono.empty();
                        fallback = Mono.just(new AccessTokenResult(cache, false));
                    } else if (cache == null || cache.isExpired()) {
                        // no token to use
                        if (now.isAfter(nextTokenRefresh)) {
                            // refresh immediately
                            tokenRefresh = Mono.defer(tokenSupplier);
                        } else {
                            // wait for timeout, then refresh
                            tokenRefresh = Mono.defer(tokenSupplier)
                                    .delaySubscription(Duration.between(now, nextTokenRefresh));
                        }
                        // cache doesn't exist or expired, no fallback
                        fallback = Mono.empty();
                    } else {
                        // token available, but close to expiry
                        if (now.isAfter(nextTokenRefresh)) {
                            // refresh immediately
                            tokenRefresh = Mono.defer(tokenSupplier);
                        } else {
                            // still in timeout, do not refresh
                            tokenRefresh = Mono.empty();
                        }
                        // cache hasn't expired, ignore refresh error this time
                        fallback = Mono.just(new AccessTokenResult(cache, false));
                    }

                    return Mono.using(() -> wip, ignored -> tokenRefresh.materialize()
                        .flatMap(processTokenRefreshResult(sinksOne, now, fallback))
                        .doOnError(sinksOne::tryEmitError), w -> w.set(null));
                } else if (cache != null && !cache.isExpired() && !checkToForceFetchToken) {
                    // another thread might be refreshing the token proactively, but the current token is still valid
                    return Mono.just(new AccessTokenResult(cache, false));
                } else {
                    // if a force refresh is possible, then exit and retry.
                    if (checkToForceFetchToken) {
                        return Mono.empty();
                    }
                    // another thread is definitely refreshing the expired token
                    Sinks.One<AccessToken> sinksOne = wip.get();
                    if (sinksOne == null) {
                        // the refreshing thread has finished
                        return Mono.just(new AccessTokenResult(cache, false));
                    } else {
                        // wait for refreshing thread to finish but defer to updated cache in case just missed onNext()
                        return sinksOne.asMono().switchIfEmpty(Mono.defer(() -> Mono.just(cache)))
                                .map(accessToken -> new AccessTokenResult(accessToken, true));
                    }
                }
            } catch (Throwable t) {
                return Mono.error(t);
            }
        };
    }

    private boolean checkIfForceRefreshRequired(TokenRequestContext tokenRequestContext) {
        return !(this.tokenRequestContext != null
                && (this.tokenRequestContext.getClaims() == null ? tokenRequestContext.getClaims() == null
                : (tokenRequestContext.getClaims() == null ? false
                : tokenRequestContext.getClaims().equals(this.tokenRequestContext.getClaims())))
                && this.tokenRequestContext.getScopes().equals(tokenRequestContext.getScopes()));
    }

    private Function<Signal<AccessToken>, Mono<? extends AccessTokenResult>> processTokenRefreshResult(
            Sinks.One<AccessToken> sinksOne, OffsetDateTime now, Mono<AccessTokenResult> fallback) {
        return signal -> {
            AccessToken accessToken = signal.get();
            Throwable error = signal.getThrowable();
            if (signal.isOnNext() && accessToken != null) { // SUCCESS
                logger.info(refreshLog(cache, now, "Acquired a new access token"));
                cache = accessToken;
                sinksOne.tryEmitValue(accessToken);
                nextTokenRefresh = OffsetDateTime.now().plus(REFRESH_DELAY);
                return Mono.just(new AccessTokenResult(accessToken, true));
            } else if (signal.isOnError() && error != null) { // ERROR
                logger.error(refreshLog(cache, now, "Failed to acquire a new access token"));
                nextTokenRefresh = OffsetDateTime.now().plus(REFRESH_DELAY);
                return fallback.switchIfEmpty(Mono.defer(() -> Mono.error(error)));
            } else { // NO REFRESH
                sinksOne.tryEmitEmpty();
                return fallback;
            }
        };
    }

    private static String refreshLog(AccessToken cache, OffsetDateTime now, String log) {
        StringBuilder info = new StringBuilder(log);
        if (cache == null) {
            info.append(".");
        } else {
            Duration tte = Duration.between(now, cache.getExpiresAt());
            info.append(" at ").append(tte.abs().getSeconds()).append(" seconds ")
                    .append(tte.isNegative() ? "after" : "before").append(" expiry. ")
                    .append("Retry may be attempted after ").append(REFRESH_DELAY.getSeconds()).append(" seconds.");
            if (!tte.isNegative()) {
                info.append(" The token currently cached will be used.");
            }
        }
        return info.toString();
    }
}
