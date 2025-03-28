// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.datafactory.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.datafactory.fluent.models.DynamicsAXResourceDatasetTypeProperties;

public final class DynamicsAXResourceDatasetTypePropertiesTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        DynamicsAXResourceDatasetTypeProperties model = BinaryData.fromString("{\"path\":\"dataztdacrqcwkk\"}")
            .toObject(DynamicsAXResourceDatasetTypeProperties.class);
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        DynamicsAXResourceDatasetTypeProperties model
            = new DynamicsAXResourceDatasetTypeProperties().withPath("dataztdacrqcwkk");
        model = BinaryData.fromObject(model).toObject(DynamicsAXResourceDatasetTypeProperties.class);
    }
}
