// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.confidentialledger.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Location of the ARM Resource.
 */
@Fluent
public class ResourceLocation implements JsonSerializable<ResourceLocation> {
    /*
     * The Azure location where the Confidential Ledger is running.
     */
    private String location;

    /**
     * Creates an instance of ResourceLocation class.
     */
    public ResourceLocation() {
    }

    /**
     * Get the location property: The Azure location where the Confidential Ledger is running.
     * 
     * @return the location value.
     */
    public String location() {
        return this.location;
    }

    /**
     * Set the location property: The Azure location where the Confidential Ledger is running.
     * 
     * @param location the location value to set.
     * @return the ResourceLocation object itself.
     */
    public ResourceLocation withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("location", this.location);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ResourceLocation from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ResourceLocation if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ResourceLocation.
     */
    public static ResourceLocation fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ResourceLocation deserializedResourceLocation = new ResourceLocation();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("location".equals(fieldName)) {
                    deserializedResourceLocation.location = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedResourceLocation;
        });
    }
}
