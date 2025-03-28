// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.reservations.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Savings plan purchase details.
 */
@Fluent
public final class SavingsPlanToPurchaseExchange implements JsonSerializable<SavingsPlanToPurchaseExchange> {
    /*
     * Fully qualified id of the savings plan order being purchased
     */
    private String savingsPlanOrderId;

    /*
     * Fully qualified id of the savings plan being purchased. This value is only guaranteed to be non-null if the
     * purchase is successful.
     */
    private String savingsPlanId;

    /*
     * Request body for savings plan purchase
     */
    private SavingsPlanPurchaseRequest properties;

    /*
     * Pricing information containing the amount and the currency code
     */
    private Price billingCurrencyTotal;

    /*
     * Status of the individual operation.
     */
    private OperationStatus status;

    /**
     * Creates an instance of SavingsPlanToPurchaseExchange class.
     */
    public SavingsPlanToPurchaseExchange() {
    }

    /**
     * Get the savingsPlanOrderId property: Fully qualified id of the savings plan order being purchased.
     * 
     * @return the savingsPlanOrderId value.
     */
    public String savingsPlanOrderId() {
        return this.savingsPlanOrderId;
    }

    /**
     * Set the savingsPlanOrderId property: Fully qualified id of the savings plan order being purchased.
     * 
     * @param savingsPlanOrderId the savingsPlanOrderId value to set.
     * @return the SavingsPlanToPurchaseExchange object itself.
     */
    public SavingsPlanToPurchaseExchange withSavingsPlanOrderId(String savingsPlanOrderId) {
        this.savingsPlanOrderId = savingsPlanOrderId;
        return this;
    }

    /**
     * Get the savingsPlanId property: Fully qualified id of the savings plan being purchased. This value is only
     * guaranteed to be non-null if the purchase is successful.
     * 
     * @return the savingsPlanId value.
     */
    public String savingsPlanId() {
        return this.savingsPlanId;
    }

    /**
     * Set the savingsPlanId property: Fully qualified id of the savings plan being purchased. This value is only
     * guaranteed to be non-null if the purchase is successful.
     * 
     * @param savingsPlanId the savingsPlanId value to set.
     * @return the SavingsPlanToPurchaseExchange object itself.
     */
    public SavingsPlanToPurchaseExchange withSavingsPlanId(String savingsPlanId) {
        this.savingsPlanId = savingsPlanId;
        return this;
    }

    /**
     * Get the properties property: Request body for savings plan purchase.
     * 
     * @return the properties value.
     */
    public SavingsPlanPurchaseRequest properties() {
        return this.properties;
    }

    /**
     * Set the properties property: Request body for savings plan purchase.
     * 
     * @param properties the properties value to set.
     * @return the SavingsPlanToPurchaseExchange object itself.
     */
    public SavingsPlanToPurchaseExchange withProperties(SavingsPlanPurchaseRequest properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the billingCurrencyTotal property: Pricing information containing the amount and the currency code.
     * 
     * @return the billingCurrencyTotal value.
     */
    public Price billingCurrencyTotal() {
        return this.billingCurrencyTotal;
    }

    /**
     * Set the billingCurrencyTotal property: Pricing information containing the amount and the currency code.
     * 
     * @param billingCurrencyTotal the billingCurrencyTotal value to set.
     * @return the SavingsPlanToPurchaseExchange object itself.
     */
    public SavingsPlanToPurchaseExchange withBillingCurrencyTotal(Price billingCurrencyTotal) {
        this.billingCurrencyTotal = billingCurrencyTotal;
        return this;
    }

    /**
     * Get the status property: Status of the individual operation.
     * 
     * @return the status value.
     */
    public OperationStatus status() {
        return this.status;
    }

    /**
     * Set the status property: Status of the individual operation.
     * 
     * @param status the status value to set.
     * @return the SavingsPlanToPurchaseExchange object itself.
     */
    public SavingsPlanToPurchaseExchange withStatus(OperationStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (properties() != null) {
            properties().validate();
        }
        if (billingCurrencyTotal() != null) {
            billingCurrencyTotal().validate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("savingsPlanOrderId", this.savingsPlanOrderId);
        jsonWriter.writeStringField("savingsPlanId", this.savingsPlanId);
        jsonWriter.writeJsonField("properties", this.properties);
        jsonWriter.writeJsonField("billingCurrencyTotal", this.billingCurrencyTotal);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SavingsPlanToPurchaseExchange from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SavingsPlanToPurchaseExchange if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the SavingsPlanToPurchaseExchange.
     */
    public static SavingsPlanToPurchaseExchange fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SavingsPlanToPurchaseExchange deserializedSavingsPlanToPurchaseExchange
                = new SavingsPlanToPurchaseExchange();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("savingsPlanOrderId".equals(fieldName)) {
                    deserializedSavingsPlanToPurchaseExchange.savingsPlanOrderId = reader.getString();
                } else if ("savingsPlanId".equals(fieldName)) {
                    deserializedSavingsPlanToPurchaseExchange.savingsPlanId = reader.getString();
                } else if ("properties".equals(fieldName)) {
                    deserializedSavingsPlanToPurchaseExchange.properties = SavingsPlanPurchaseRequest.fromJson(reader);
                } else if ("billingCurrencyTotal".equals(fieldName)) {
                    deserializedSavingsPlanToPurchaseExchange.billingCurrencyTotal = Price.fromJson(reader);
                } else if ("status".equals(fieldName)) {
                    deserializedSavingsPlanToPurchaseExchange.status = OperationStatus.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSavingsPlanToPurchaseExchange;
        });
    }
}
