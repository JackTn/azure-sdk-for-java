// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.nginx.generated;

import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpClient;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.models.AzureCloud;
import com.azure.core.test.http.MockHttpResponse;
import com.azure.resourcemanager.nginx.NginxManager;
import com.azure.resourcemanager.nginx.models.NginxDeploymentApiKeyResponse;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public final class ApiKeysListMockTests {
    @Test
    public void testList() throws Exception {
        String responseStr
            = "{\"value\":[{\"properties\":{\"hint\":\"jhemms\",\"endDateTime\":\"2021-07-03T07:44:33Z\"},\"id\":\"kcrodtjinfw\",\"name\":\"lfltka\",\"type\":\"jvefkdlfoakggkfp\"}]}";

        HttpClient httpClient
            = response -> Mono.just(new MockHttpResponse(response, 200, responseStr.getBytes(StandardCharsets.UTF_8)));
        NginxManager manager = NginxManager.configure()
            .withHttpClient(httpClient)
            .authenticate(tokenRequestContext -> Mono.just(new AccessToken("this_is_a_token", OffsetDateTime.MAX)),
                new AzureProfile("", "", AzureCloud.AZURE_PUBLIC_CLOUD));

        PagedIterable<NginxDeploymentApiKeyResponse> response
            = manager.apiKeys().list("tdooaoj", "niodkooeb", com.azure.core.util.Context.NONE);

        Assertions.assertEquals(OffsetDateTime.parse("2021-07-03T07:44:33Z"),
            response.iterator().next().properties().endDateTime());
    }
}
