// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.postgresqlflexibleserver.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Flag to enable / disable Storage Auto grow for flexible server.
 */
public final class StorageAutoGrow extends ExpandableStringEnum<StorageAutoGrow> {
    /**
     * Static value Enabled for StorageAutoGrow.
     */
    public static final StorageAutoGrow ENABLED = fromString("Enabled");

    /**
     * Static value Disabled for StorageAutoGrow.
     */
    public static final StorageAutoGrow DISABLED = fromString("Disabled");

    /**
     * Creates a new instance of StorageAutoGrow value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public StorageAutoGrow() {
    }

    /**
     * Creates or finds a StorageAutoGrow from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding StorageAutoGrow.
     */
    public static StorageAutoGrow fromString(String name) {
        return fromString(name, StorageAutoGrow.class);
    }

    /**
     * Gets known StorageAutoGrow values.
     * 
     * @return known StorageAutoGrow values.
     */
    public static Collection<StorageAutoGrow> values() {
        return values(StorageAutoGrow.class);
    }
}
