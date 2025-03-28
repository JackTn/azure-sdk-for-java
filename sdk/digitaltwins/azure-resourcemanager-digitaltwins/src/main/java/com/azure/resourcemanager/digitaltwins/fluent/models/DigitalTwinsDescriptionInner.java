// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.digitaltwins.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.management.SystemData;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.azure.resourcemanager.digitaltwins.models.DigitalTwinsIdentity;
import com.azure.resourcemanager.digitaltwins.models.DigitalTwinsResource;
import com.azure.resourcemanager.digitaltwins.models.ProvisioningState;
import com.azure.resourcemanager.digitaltwins.models.PublicNetworkAccess;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * The description of the DigitalTwins service.
 */
@Fluent
public final class DigitalTwinsDescriptionInner extends DigitalTwinsResource {
    /*
     * DigitalTwins instance properties.
     */
    private DigitalTwinsProperties innerProperties;

    /*
     * Metadata pertaining to creation and last modification of the DigitalTwinsInstance.
     */
    private SystemData systemData;

    /*
     * The type of the resource.
     */
    private String type;

    /*
     * The name of the resource.
     */
    private String name;

    /*
     * Fully qualified resource Id for the resource.
     */
    private String id;

    /**
     * Creates an instance of DigitalTwinsDescriptionInner class.
     */
    public DigitalTwinsDescriptionInner() {
    }

    /**
     * Get the innerProperties property: DigitalTwins instance properties.
     * 
     * @return the innerProperties value.
     */
    private DigitalTwinsProperties innerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the systemData property: Metadata pertaining to creation and last modification of the DigitalTwinsInstance.
     * 
     * @return the systemData value.
     */
    @Override
    public SystemData systemData() {
        return this.systemData;
    }

    /**
     * Get the type property: The type of the resource.
     * 
     * @return the type value.
     */
    @Override
    public String type() {
        return this.type;
    }

    /**
     * Get the name property: The name of the resource.
     * 
     * @return the name value.
     */
    @Override
    public String name() {
        return this.name;
    }

    /**
     * Get the id property: Fully qualified resource Id for the resource.
     * 
     * @return the id value.
     */
    @Override
    public String id() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DigitalTwinsDescriptionInner withIdentity(DigitalTwinsIdentity identity) {
        super.withIdentity(identity);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DigitalTwinsDescriptionInner withLocation(String location) {
        super.withLocation(location);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DigitalTwinsDescriptionInner withTags(Map<String, String> tags) {
        super.withTags(tags);
        return this;
    }

    /**
     * Get the createdTime property: Time when DigitalTwinsInstance was created.
     * 
     * @return the createdTime value.
     */
    public OffsetDateTime createdTime() {
        return this.innerProperties() == null ? null : this.innerProperties().createdTime();
    }

    /**
     * Get the lastUpdatedTime property: Time when DigitalTwinsInstance was updated.
     * 
     * @return the lastUpdatedTime value.
     */
    public OffsetDateTime lastUpdatedTime() {
        return this.innerProperties() == null ? null : this.innerProperties().lastUpdatedTime();
    }

    /**
     * Get the provisioningState property: The provisioning state.
     * 
     * @return the provisioningState value.
     */
    public ProvisioningState provisioningState() {
        return this.innerProperties() == null ? null : this.innerProperties().provisioningState();
    }

    /**
     * Get the hostname property: Api endpoint to work with DigitalTwinsInstance.
     * 
     * @return the hostname value.
     */
    public String hostname() {
        return this.innerProperties() == null ? null : this.innerProperties().hostname();
    }

    /**
     * Get the privateEndpointConnections property: The private endpoint connections.
     * 
     * @return the privateEndpointConnections value.
     */
    public List<PrivateEndpointConnectionInner> privateEndpointConnections() {
        return this.innerProperties() == null ? null : this.innerProperties().privateEndpointConnections();
    }

    /**
     * Set the privateEndpointConnections property: The private endpoint connections.
     * 
     * @param privateEndpointConnections the privateEndpointConnections value to set.
     * @return the DigitalTwinsDescriptionInner object itself.
     */
    public DigitalTwinsDescriptionInner
        withPrivateEndpointConnections(List<PrivateEndpointConnectionInner> privateEndpointConnections) {
        if (this.innerProperties() == null) {
            this.innerProperties = new DigitalTwinsProperties();
        }
        this.innerProperties().withPrivateEndpointConnections(privateEndpointConnections);
        return this;
    }

    /**
     * Get the publicNetworkAccess property: Public network access for the DigitalTwinsInstance.
     * 
     * @return the publicNetworkAccess value.
     */
    public PublicNetworkAccess publicNetworkAccess() {
        return this.innerProperties() == null ? null : this.innerProperties().publicNetworkAccess();
    }

    /**
     * Set the publicNetworkAccess property: Public network access for the DigitalTwinsInstance.
     * 
     * @param publicNetworkAccess the publicNetworkAccess value to set.
     * @return the DigitalTwinsDescriptionInner object itself.
     */
    public DigitalTwinsDescriptionInner withPublicNetworkAccess(PublicNetworkAccess publicNetworkAccess) {
        if (this.innerProperties() == null) {
            this.innerProperties = new DigitalTwinsProperties();
        }
        this.innerProperties().withPublicNetworkAccess(publicNetworkAccess);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        if (innerProperties() != null) {
            innerProperties().validate();
        }
        if (identity() != null) {
            identity().validate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("location", location());
        jsonWriter.writeMapField("tags", tags(), (writer, element) -> writer.writeString(element));
        jsonWriter.writeJsonField("identity", identity());
        jsonWriter.writeJsonField("properties", this.innerProperties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DigitalTwinsDescriptionInner from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DigitalTwinsDescriptionInner if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DigitalTwinsDescriptionInner.
     */
    public static DigitalTwinsDescriptionInner fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DigitalTwinsDescriptionInner deserializedDigitalTwinsDescriptionInner = new DigitalTwinsDescriptionInner();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.id = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.name = reader.getString();
                } else if ("type".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.type = reader.getString();
                } else if ("location".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.withLocation(reader.getString());
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedDigitalTwinsDescriptionInner.withTags(tags);
                } else if ("identity".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.withIdentity(DigitalTwinsIdentity.fromJson(reader));
                } else if ("systemData".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.systemData = SystemData.fromJson(reader);
                } else if ("properties".equals(fieldName)) {
                    deserializedDigitalTwinsDescriptionInner.innerProperties = DigitalTwinsProperties.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDigitalTwinsDescriptionInner;
        });
    }
}
