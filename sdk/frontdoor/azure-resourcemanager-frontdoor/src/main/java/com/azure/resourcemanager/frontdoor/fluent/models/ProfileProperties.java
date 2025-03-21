// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.frontdoor.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.azure.resourcemanager.frontdoor.models.NetworkExperimentResourceState;
import com.azure.resourcemanager.frontdoor.models.State;
import java.io.IOException;

/**
 * Defines the properties of an experiment.
 */
@Fluent
public final class ProfileProperties implements JsonSerializable<ProfileProperties> {
    /*
     * Resource status.
     */
    private NetworkExperimentResourceState resourceState;

    /*
     * The state of the Experiment
     */
    private State enabledState;

    /**
     * Creates an instance of ProfileProperties class.
     */
    public ProfileProperties() {
    }

    /**
     * Get the resourceState property: Resource status.
     * 
     * @return the resourceState value.
     */
    public NetworkExperimentResourceState resourceState() {
        return this.resourceState;
    }

    /**
     * Get the enabledState property: The state of the Experiment.
     * 
     * @return the enabledState value.
     */
    public State enabledState() {
        return this.enabledState;
    }

    /**
     * Set the enabledState property: The state of the Experiment.
     * 
     * @param enabledState the enabledState value to set.
     * @return the ProfileProperties object itself.
     */
    public ProfileProperties withEnabledState(State enabledState) {
        this.enabledState = enabledState;
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
        jsonWriter.writeStringField("enabledState", this.enabledState == null ? null : this.enabledState.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProfileProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProfileProperties if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProfileProperties.
     */
    public static ProfileProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProfileProperties deserializedProfileProperties = new ProfileProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("resourceState".equals(fieldName)) {
                    deserializedProfileProperties.resourceState
                        = NetworkExperimentResourceState.fromString(reader.getString());
                } else if ("enabledState".equals(fieldName)) {
                    deserializedProfileProperties.enabledState = State.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProfileProperties;
        });
    }
}
