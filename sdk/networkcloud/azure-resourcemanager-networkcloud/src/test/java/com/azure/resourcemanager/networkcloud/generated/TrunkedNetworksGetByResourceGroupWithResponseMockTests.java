// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.networkcloud.generated;

import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpClient;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.models.AzureCloud;
import com.azure.core.test.http.MockHttpResponse;
import com.azure.resourcemanager.networkcloud.NetworkCloudManager;
import com.azure.resourcemanager.networkcloud.models.HybridAksPluginType;
import com.azure.resourcemanager.networkcloud.models.TrunkedNetwork;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public final class TrunkedNetworksGetByResourceGroupWithResponseMockTests {
    @Test
    public void testGetByResourceGroupWithResponse() throws Exception {
        String responseStr
            = "{\"extendedLocation\":{\"name\":\"rulfuct\",\"type\":\"jrthcfjzhx\"},\"properties\":{\"associatedResourceIds\":[\"bqjr\",\"stvrjeqmt\"],\"clusterId\":\"beqrztrx\",\"detailedStatus\":\"Available\",\"detailedStatusMessage\":\"d\",\"hybridAksClustersAssociatedIds\":[\"srwrsnrhpqati\",\"kkvyanxk\",\"vcsemsvuvdj\",\"qxetqmmlivrjjx\"],\"hybridAksPluginType\":\"OSDevice\",\"interfaceName\":\"chp\",\"isolationDomainIds\":[\"xlehzlxpgfquwzp\",\"iibelwcerw\"],\"provisioningState\":\"Canceled\",\"virtualMachinesAssociatedIds\":[\"xljtxbusqtbxxn\",\"uisdzhgbdgz\",\"agsecnadbuw\"],\"vlans\":[7056354407453453620]},\"location\":\"llmqiyne\",\"tags\":{\"kiiwvmtu\":\"lln\",\"vqpilrguncanldu\":\"xpymdjfuaxro\"},\"id\":\"zorxsb\",\"name\":\"x\",\"type\":\"qk\"}";

        HttpClient httpClient
            = response -> Mono.just(new MockHttpResponse(response, 200, responseStr.getBytes(StandardCharsets.UTF_8)));
        NetworkCloudManager manager = NetworkCloudManager.configure()
            .withHttpClient(httpClient)
            .authenticate(tokenRequestContext -> Mono.just(new AccessToken("this_is_a_token", OffsetDateTime.MAX)),
                new AzureProfile("", "", AzureCloud.AZURE_PUBLIC_CLOUD));

        TrunkedNetwork response = manager.trunkedNetworks()
            .getByResourceGroupWithResponse("sbvr", "aqgvto", com.azure.core.util.Context.NONE)
            .getValue();

        Assertions.assertEquals("llmqiyne", response.location());
        Assertions.assertEquals("lln", response.tags().get("kiiwvmtu"));
        Assertions.assertEquals("rulfuct", response.extendedLocation().name());
        Assertions.assertEquals("jrthcfjzhx", response.extendedLocation().type());
        Assertions.assertEquals(HybridAksPluginType.OSDEVICE, response.hybridAksPluginType());
        Assertions.assertEquals("chp", response.interfaceName());
        Assertions.assertEquals("xlehzlxpgfquwzp", response.isolationDomainIds().get(0));
        Assertions.assertEquals(7056354407453453620L, response.vlans().get(0));
    }
}
