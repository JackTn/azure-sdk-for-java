// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.storage.models;

import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.azure.resourcemanager.storage.fluent.models.FileServiceUsageInner;
import java.io.IOException;
import java.util.List;

/**
 * List file service usages schema.
 */
@Immutable
public final class FileServiceUsages implements JsonSerializable<FileServiceUsages> {
    /*
     * List of file service usages returned.
     */
    private List<FileServiceUsageInner> value;

    /*
     * Request URL that can be used to query next page of file service usages. Returned when total number of requested
     * file service usages exceed maximum page size.
     */
    private String nextLink;

    /**
     * Creates an instance of FileServiceUsages class.
     */
    public FileServiceUsages() {
    }

    /**
     * Get the value property: List of file service usages returned.
     * 
     * @return the value value.
     */
    public List<FileServiceUsageInner> value() {
        return this.value;
    }

    /**
     * Get the nextLink property: Request URL that can be used to query next page of file service usages. Returned when
     * total number of requested file service usages exceed maximum page size.
     * 
     * @return the nextLink value.
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (value() != null) {
            value().forEach(e -> e.validate());
        }
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
     * Reads an instance of FileServiceUsages from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of FileServiceUsages if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the FileServiceUsages.
     */
    public static FileServiceUsages fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            FileServiceUsages deserializedFileServiceUsages = new FileServiceUsages();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    List<FileServiceUsageInner> value
                        = reader.readArray(reader1 -> FileServiceUsageInner.fromJson(reader1));
                    deserializedFileServiceUsages.value = value;
                } else if ("nextLink".equals(fieldName)) {
                    deserializedFileServiceUsages.nextLink = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFileServiceUsages;
        });
    }
}
