// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.compute.generated;

import com.azure.resourcemanager.compute.models.ExpandTypesForGetCapacityReservationGroups;
import com.azure.resourcemanager.compute.models.ResourceIdOptionsForGetCapacityReservationGroups;

/**
 * Samples for CapacityReservationGroups List.
 */
public final class CapacityReservationGroupsListSamples {
    /*
     * x-ms-original-file:
     * specification/compute/resource-manager/Microsoft.Compute/ComputeRP/stable/2024-11-01/examples/
     * capacityReservationExamples/CapacityReservationGroup_ListBySubscription.json
     */
    /**
     * Sample code: List capacity reservation groups in subscription.
     * 
     * @param azure The entry point for accessing resource management APIs in Azure.
     */
    public static void
        listCapacityReservationGroupsInSubscription(com.azure.resourcemanager.AzureResourceManager azure) {
        azure.virtualMachines()
            .manager()
            .serviceClient()
            .getCapacityReservationGroups()
            .list(ExpandTypesForGetCapacityReservationGroups.VIRTUAL_MACHINES_REF, null,
                com.azure.core.util.Context.NONE);
    }

    /*
     * x-ms-original-file:
     * specification/compute/resource-manager/Microsoft.Compute/ComputeRP/stable/2024-11-01/examples/
     * capacityReservationExamples/CapacityReservationGroup_ListBySubscriptionWithResourceIdsQuery.json
     */
    /**
     * Sample code: List capacity reservation groups with resource Ids only in subscription.
     * 
     * @param azure The entry point for accessing resource management APIs in Azure.
     */
    public static void listCapacityReservationGroupsWithResourceIdsOnlyInSubscription(
        com.azure.resourcemanager.AzureResourceManager azure) {
        azure.virtualMachines()
            .manager()
            .serviceClient()
            .getCapacityReservationGroups()
            .list(null, ResourceIdOptionsForGetCapacityReservationGroups.ALL, com.azure.core.util.Context.NONE);
    }
}
