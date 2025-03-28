// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.kubernetesconfiguration.implementation;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.azure.resourcemanager.kubernetesconfiguration.fluent.OperationsClient;
import com.azure.resourcemanager.kubernetesconfiguration.fluent.models.ResourceProviderOperationInner;
import com.azure.resourcemanager.kubernetesconfiguration.models.Operations;
import com.azure.resourcemanager.kubernetesconfiguration.models.ResourceProviderOperation;

public final class OperationsImpl implements Operations {
    private static final ClientLogger LOGGER = new ClientLogger(OperationsImpl.class);

    private final OperationsClient innerClient;

    private final com.azure.resourcemanager.kubernetesconfiguration.SourceControlConfigurationManager serviceManager;

    public OperationsImpl(OperationsClient innerClient,
        com.azure.resourcemanager.kubernetesconfiguration.SourceControlConfigurationManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public PagedIterable<ResourceProviderOperation> list() {
        PagedIterable<ResourceProviderOperationInner> inner = this.serviceClient().list();
        return ResourceManagerUtils.mapPage(inner, inner1 -> new ResourceProviderOperationImpl(inner1, this.manager()));
    }

    public PagedIterable<ResourceProviderOperation> list(Context context) {
        PagedIterable<ResourceProviderOperationInner> inner = this.serviceClient().list(context);
        return ResourceManagerUtils.mapPage(inner, inner1 -> new ResourceProviderOperationImpl(inner1, this.manager()));
    }

    private OperationsClient serviceClient() {
        return this.innerClient;
    }

    private com.azure.resourcemanager.kubernetesconfiguration.SourceControlConfigurationManager manager() {
        return this.serviceManager;
    }
}
