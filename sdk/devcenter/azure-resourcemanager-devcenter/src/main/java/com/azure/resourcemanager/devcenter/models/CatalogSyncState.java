// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.devcenter.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * The synchronization state of the catalog.
 */
public final class CatalogSyncState extends ExpandableStringEnum<CatalogSyncState> {
    /**
     * Static value Succeeded for CatalogSyncState.
     */
    public static final CatalogSyncState SUCCEEDED = fromString("Succeeded");

    /**
     * Static value InProgress for CatalogSyncState.
     */
    public static final CatalogSyncState IN_PROGRESS = fromString("InProgress");

    /**
     * Static value Failed for CatalogSyncState.
     */
    public static final CatalogSyncState FAILED = fromString("Failed");

    /**
     * Static value Canceled for CatalogSyncState.
     */
    public static final CatalogSyncState CANCELED = fromString("Canceled");

    /**
     * Creates a new instance of CatalogSyncState value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public CatalogSyncState() {
    }

    /**
     * Creates or finds a CatalogSyncState from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding CatalogSyncState.
     */
    public static CatalogSyncState fromString(String name) {
        return fromString(name, CatalogSyncState.class);
    }

    /**
     * Gets known CatalogSyncState values.
     * 
     * @return known CatalogSyncState values.
     */
    public static Collection<CatalogSyncState> values() {
        return values(CatalogSyncState.class);
    }
}
