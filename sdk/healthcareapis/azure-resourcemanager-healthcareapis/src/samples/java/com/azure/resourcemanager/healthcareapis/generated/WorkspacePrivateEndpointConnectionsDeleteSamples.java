// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.healthcareapis.generated;

/**
 * Samples for WorkspacePrivateEndpointConnections Delete.
 */
public final class WorkspacePrivateEndpointConnectionsDeleteSamples {
    /*
     * x-ms-original-file:
     * specification/healthcareapis/resource-manager/Microsoft.HealthcareApis/stable/2024-03-31/examples/privatelink/
     * WorkspaceDeletePrivateEndpointConnection.json
     */
    /**
     * Sample code: WorkspacePrivateEndpointConnections_Delete.
     * 
     * @param manager Entry point to HealthcareApisManager.
     */
    public static void workspacePrivateEndpointConnectionsDelete(
        com.azure.resourcemanager.healthcareapis.HealthcareApisManager manager) {
        manager.workspacePrivateEndpointConnections()
            .delete("testRG", "workspace1", "myConnection", com.azure.core.util.Context.NONE);
    }
}
