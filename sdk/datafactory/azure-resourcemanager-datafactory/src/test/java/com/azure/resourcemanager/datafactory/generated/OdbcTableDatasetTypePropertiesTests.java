// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.datafactory.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.datafactory.fluent.models.OdbcTableDatasetTypeProperties;

public final class OdbcTableDatasetTypePropertiesTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        OdbcTableDatasetTypeProperties model = BinaryData.fromString("{\"tableName\":\"dataglxoqwbztilqb\"}")
            .toObject(OdbcTableDatasetTypeProperties.class);
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        OdbcTableDatasetTypeProperties model = new OdbcTableDatasetTypeProperties().withTableName("dataglxoqwbztilqb");
        model = BinaryData.fromObject(model).toObject(OdbcTableDatasetTypeProperties.class);
    }
}
