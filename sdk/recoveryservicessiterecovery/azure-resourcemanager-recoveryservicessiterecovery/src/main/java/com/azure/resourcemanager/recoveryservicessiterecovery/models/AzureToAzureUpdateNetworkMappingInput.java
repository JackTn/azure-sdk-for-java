// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.recoveryservicessiterecovery.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Updates network mappings input.
 */
@Fluent
public final class AzureToAzureUpdateNetworkMappingInput extends FabricSpecificUpdateNetworkMappingInput {
    /*
     * The instance type.
     */
    private String instanceType = "AzureToAzure";

    /*
     * The primary azure vnet Id.
     */
    private String primaryNetworkId;

    /**
     * Creates an instance of AzureToAzureUpdateNetworkMappingInput class.
     */
    public AzureToAzureUpdateNetworkMappingInput() {
    }

    /**
     * Get the instanceType property: The instance type.
     * 
     * @return the instanceType value.
     */
    @Override
    public String instanceType() {
        return this.instanceType;
    }

    /**
     * Get the primaryNetworkId property: The primary azure vnet Id.
     * 
     * @return the primaryNetworkId value.
     */
    public String primaryNetworkId() {
        return this.primaryNetworkId;
    }

    /**
     * Set the primaryNetworkId property: The primary azure vnet Id.
     * 
     * @param primaryNetworkId the primaryNetworkId value to set.
     * @return the AzureToAzureUpdateNetworkMappingInput object itself.
     */
    public AzureToAzureUpdateNetworkMappingInput withPrimaryNetworkId(String primaryNetworkId) {
        this.primaryNetworkId = primaryNetworkId;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("instanceType", this.instanceType);
        jsonWriter.writeStringField("primaryNetworkId", this.primaryNetworkId);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AzureToAzureUpdateNetworkMappingInput from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AzureToAzureUpdateNetworkMappingInput if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the AzureToAzureUpdateNetworkMappingInput.
     */
    public static AzureToAzureUpdateNetworkMappingInput fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AzureToAzureUpdateNetworkMappingInput deserializedAzureToAzureUpdateNetworkMappingInput
                = new AzureToAzureUpdateNetworkMappingInput();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("instanceType".equals(fieldName)) {
                    deserializedAzureToAzureUpdateNetworkMappingInput.instanceType = reader.getString();
                } else if ("primaryNetworkId".equals(fieldName)) {
                    deserializedAzureToAzureUpdateNetworkMappingInput.primaryNetworkId = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAzureToAzureUpdateNetworkMappingInput;
        });
    }
}
