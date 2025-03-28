// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.kusto.models;

import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Describes The SKU capabilities object.
 */
@Immutable
public final class ResourceSkuCapabilities implements JsonSerializable<ResourceSkuCapabilities> {
    /*
     * An invariant to describe the feature.
     */
    private String name;

    /*
     * An invariant if the feature is measured by quantity.
     */
    private String value;

    /**
     * Creates an instance of ResourceSkuCapabilities class.
     */
    public ResourceSkuCapabilities() {
    }

    /**
     * Get the name property: An invariant to describe the feature.
     * 
     * @return the name value.
     */
    public String name() {
        return this.name;
    }

    /**
     * Get the value property: An invariant if the feature is measured by quantity.
     * 
     * @return the value value.
     */
    public String value() {
        return this.value;
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
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ResourceSkuCapabilities from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ResourceSkuCapabilities if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ResourceSkuCapabilities.
     */
    public static ResourceSkuCapabilities fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ResourceSkuCapabilities deserializedResourceSkuCapabilities = new ResourceSkuCapabilities();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedResourceSkuCapabilities.name = reader.getString();
                } else if ("value".equals(fieldName)) {
                    deserializedResourceSkuCapabilities.value = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedResourceSkuCapabilities;
        });
    }
}
