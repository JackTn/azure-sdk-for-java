// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.communication.callingserver;

import com.azure.communication.callingserver.implementation.models.AcsCallParticipantInternal;
import com.azure.communication.callingserver.implementation.models.AddParticipantsResponseInternal;
import com.azure.communication.callingserver.implementation.models.CallConnectionPropertiesInternal;
import com.azure.communication.callingserver.implementation.models.CallConnectionStateModelInternal;
import com.azure.communication.callingserver.implementation.models.CallSourceInternal;
import com.azure.communication.callingserver.implementation.models.GetParticipantsResponseInternal;
import com.azure.communication.callingserver.models.MediaStreamingAudioChannelType;
import com.azure.communication.callingserver.models.MediaStreamingConfiguration;
import com.azure.communication.callingserver.models.MediaStreamingContentType;
import com.azure.communication.callingserver.models.MediaStreamingTransportType;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.json.JsonSerializable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CallAutomationUnitTestBase {
    static final String MOCK_ENDPOINT = "https://REDACTED.communication.azure.com/";
    static final String MOCK_CONNECTION_STRING = String.format("endpoint=%s;accesskey=eyJhbG", MOCK_ENDPOINT);
    static final String CALL_CONNECTION_ID = "callConnectionId";
    static final String CALL_SERVER_CALL_ID = "serverCallId";
    static final String CALL_CALLER_ID = "callerId";
    static final String CALL_TARGET_ID = "targetId";
    static final String CALL_CONNECTION_STATE = "connected";
    static final String CALL_SUBJECT = "subject";
    static final String CALL_CALLBACK_URL = "https://REDACTED.com/events";
    static final String CALL_INCOMING_CALL_CONTEXT = "eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.REDACTED";
    static final String CALL_OPERATION_CONTEXT = "operationContext";

    static final MediaStreamingConfiguration MEDIA_STREAMING_CONFIGURATION
        = new MediaStreamingConfiguration("https://websocket.url.com", MediaStreamingTransportType.WEBSOCKET,
            MediaStreamingContentType.AUDIO, MediaStreamingAudioChannelType.MIXED);

    public static String generateDownloadResult(String content) {
        return content;
    }

    public static String generateCallProperties(String callConnectionId, String serverCallId, String callerId,
        String targetId, String connectionState, String subject, String callbackUri) {
        CallConnectionPropertiesInternal result
            = new CallConnectionPropertiesInternal().setCallConnectionId(callConnectionId)
                .setServerCallId(serverCallId)
                .setCallbackUri(callbackUri)
                .setSubject(subject)
                .setCallConnectionState(CallConnectionStateModelInternal.fromString(connectionState))
                .setSource(new CallSourceInternal().setIdentifier(ModelGenerator.generateUserIdentifierModel(callerId)))
                .setTargets(
                    new ArrayList<>(Collections.singletonList(ModelGenerator.generateUserIdentifierModel(targetId))));

        return serializeObject(result);
    }

    public static String generateGetParticipantResponse(String callerId, boolean isMuted) {
        AcsCallParticipantInternal callParticipant
            = ModelGenerator.generateAcsCallParticipantInternal(callerId, isMuted);
        return serializeObject(callParticipant);
    }

    public static String generateListParticipantsResponse() {
        GetParticipantsResponseInternal getParticipantsResponseInternal
            = new GetParticipantsResponseInternal()
                .setValues(new ArrayList<>(
                    Arrays.asList(ModelGenerator.generateAcsCallParticipantInternal(CALL_CALLER_ID, false),
                        ModelGenerator.generateAcsCallParticipantInternal(CALL_TARGET_ID, true))))
                .setNextLink("");

        return serializeObject(getParticipantsResponseInternal);
    }

    public static String generateAddParticipantsResponse() {
        AddParticipantsResponseInternal addParticipantsResponseInternal = new AddParticipantsResponseInternal()
            .setOperationContext(CALL_OPERATION_CONTEXT)
            .setParticipants(new ArrayList<>(
                Collections.singletonList(ModelGenerator.generateAcsCallParticipantInternal(CALL_TARGET_ID, false))));

        return serializeObject(addParticipantsResponseInternal);
    }

    public static CallAutomationAsyncClient
        getCallAutomationAsyncClient(ArrayList<SimpleEntry<String, Integer>> responses) {
        HttpClient mockHttpClient = new MockHttpClient(responses);

        return new CallAutomationClientBuilder().httpClient(mockHttpClient)
            .connectionString(MOCK_CONNECTION_STRING)
            .buildAsyncClient();
    }

    public static CallAutomationClient getCallAutomationClient(ArrayList<SimpleEntry<String, Integer>> responses) {
        HttpClient mockHttpClient = new MockHttpClient(responses);

        return new CallAutomationClientBuilder().httpClient(mockHttpClient)
            .connectionString(MOCK_CONNECTION_STRING)
            .buildClient();
    }

    public static CallConnection getCallConnection(ArrayList<SimpleEntry<String, Integer>> responses) {
        CallAutomationClient callAutomationClient = getCallAutomationClient(responses);
        return callAutomationClient.getCallConnection("callConnectionId");
    }

    public static CallConnectionAsync getCallConnectionAsync(ArrayList<SimpleEntry<String, Integer>> responses) {
        CallAutomationAsyncClient callingServerClient = getCallAutomationAsyncClient(responses);
        return callingServerClient.getCallConnectionAsync("callConnectionId");
    }

    public static HttpResponse generateMockResponse(String body, HttpRequest request, int statusCode) {
        return new HttpResponse(request) {
            @Override
            public int getStatusCode() {
                return statusCode;
            }

            @Override
            public String getHeaderValue(String name) {
                return null;
            }

            @Override
            public HttpHeaders getHeaders() {
                return new HttpHeaders();
            }

            @Override
            public Flux<ByteBuffer> getBody() {
                return Flux.just(ByteBuffer.wrap(body.getBytes(StandardCharsets.UTF_8)));
            }

            @Override
            public Mono<byte[]> getBodyAsByteArray() {
                return Mono.just(body.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public Mono<String> getBodyAsString() {
                return Mono.just(body);
            }

            @Override
            public Mono<String> getBodyAsString(Charset charset) {
                return Mono.just(body);
            }
        };
    }

    static String serializeObject(JsonSerializable<?> o) {
        try {
            return o.toJsonString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
