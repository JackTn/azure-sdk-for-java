// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.applicationinsights.implementation;

import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.azure.resourcemanager.applicationinsights.fluent.LiveTokensClient;
import com.azure.resourcemanager.applicationinsights.fluent.models.LiveTokenResponseInner;
import com.azure.resourcemanager.applicationinsights.models.LiveTokenResponse;
import com.azure.resourcemanager.applicationinsights.models.LiveTokens;

public final class LiveTokensImpl implements LiveTokens {
    private static final ClientLogger LOGGER = new ClientLogger(LiveTokensImpl.class);

    private final LiveTokensClient innerClient;

    private final com.azure.resourcemanager.applicationinsights.ApplicationInsightsManager serviceManager;

    public LiveTokensImpl(LiveTokensClient innerClient,
        com.azure.resourcemanager.applicationinsights.ApplicationInsightsManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public Response<LiveTokenResponse> getWithResponse(String resourceUri, Context context) {
        Response<LiveTokenResponseInner> inner = this.serviceClient().getWithResponse(resourceUri, context);
        if (inner != null) {
            return new SimpleResponse<>(inner.getRequest(), inner.getStatusCode(), inner.getHeaders(),
                new LiveTokenResponseImpl(inner.getValue(), this.manager()));
        } else {
            return null;
        }
    }

    public LiveTokenResponse get(String resourceUri) {
        LiveTokenResponseInner inner = this.serviceClient().get(resourceUri);
        if (inner != null) {
            return new LiveTokenResponseImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    private LiveTokensClient serviceClient() {
        return this.innerClient;
    }

    private com.azure.resourcemanager.applicationinsights.ApplicationInsightsManager manager() {
        return this.serviceManager;
    }
}
