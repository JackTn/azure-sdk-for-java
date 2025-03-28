// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.sqlvirtualmachine.generated;

/**
 * Samples for SqlVirtualMachineGroups Delete.
 */
public final class SqlVirtualMachineGroupsDeleteSamples {
    /*
     * x-ms-original-file:
     * specification/sqlvirtualmachine/resource-manager/Microsoft.SqlVirtualMachine/preview/2022-08-01-preview/examples/
     * DeleteSqlVirtualMachineGroup.json
     */
    /**
     * Sample code: Deletes a SQL virtual machine group.
     * 
     * @param manager Entry point to SqlVirtualMachineManager.
     */
    public static void
        deletesASQLVirtualMachineGroup(com.azure.resourcemanager.sqlvirtualmachine.SqlVirtualMachineManager manager) {
        manager.sqlVirtualMachineGroups().delete("testrg", "testvmgroup", com.azure.core.util.Context.NONE);
    }
}
