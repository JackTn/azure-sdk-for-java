// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.recoveryservicessiterecovery.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Map;

/**
 * This class represents job details based on specific job type.
 */
@Fluent
public final class AsrJobDetails extends JobDetails {
    /*
     * Gets the type of job details (see JobDetailsTypes enum for possible values).
     */
    private String instanceType = "AsrJobDetails";

    /**
     * Creates an instance of AsrJobDetails class.
     */
    public AsrJobDetails() {
    }

    /**
     * Get the instanceType property: Gets the type of job details (see JobDetailsTypes enum for possible values).
     * 
     * @return the instanceType value.
     */
    @Override
    public String instanceType() {
        return this.instanceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AsrJobDetails withAffectedObjectDetails(Map<String, String> affectedObjectDetails) {
        super.withAffectedObjectDetails(affectedObjectDetails);
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
        jsonWriter.writeMapField("affectedObjectDetails", affectedObjectDetails(),
            (writer, element) -> writer.writeString(element));
        jsonWriter.writeStringField("instanceType", this.instanceType);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AsrJobDetails from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AsrJobDetails if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the AsrJobDetails.
     */
    public static AsrJobDetails fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AsrJobDetails deserializedAsrJobDetails = new AsrJobDetails();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("affectedObjectDetails".equals(fieldName)) {
                    Map<String, String> affectedObjectDetails = reader.readMap(reader1 -> reader1.getString());
                    deserializedAsrJobDetails.withAffectedObjectDetails(affectedObjectDetails);
                } else if ("instanceType".equals(fieldName)) {
                    deserializedAsrJobDetails.instanceType = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAsrJobDetails;
        });
    }
}
