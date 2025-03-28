// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.storageactions.models;

/**
 * Action to be taken when the operation is successful for a object.
 */
public enum OnSuccess {
    /**
     * Enum value continue.
     */
    CONTINUE("continue");

    /**
     * The actual serialized value for a OnSuccess instance.
     */
    private final String value;

    OnSuccess(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a OnSuccess instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed OnSuccess object, or null if unable to parse.
     */
    public static OnSuccess fromString(String value) {
        if (value == null) {
            return null;
        }
        OnSuccess[] items = OnSuccess.values();
        for (OnSuccess item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
