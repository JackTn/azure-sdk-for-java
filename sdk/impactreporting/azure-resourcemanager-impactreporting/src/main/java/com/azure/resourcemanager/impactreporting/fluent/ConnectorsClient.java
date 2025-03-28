// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.azure.resourcemanager.impactreporting.fluent;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.management.polling.PollResult;
import com.azure.core.util.Context;
import com.azure.core.util.polling.SyncPoller;
import com.azure.resourcemanager.impactreporting.fluent.models.ConnectorInner;
import com.azure.resourcemanager.impactreporting.models.ConnectorUpdate;

/**
 * An instance of this class provides access to all the operations defined in ConnectorsClient.
 */
public interface ConnectorsClient {
    /**
     * Get a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a Connector along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<ConnectorInner> getWithResponse(String connectorName, Context context);

    /**
     * Get a Connector.
     * 
     * @param connectorName The name of the connector.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a Connector.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ConnectorInner get(String connectorName);

    /**
     * Create a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param resource Resource create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of a connector is a resource that can be used to proactively report
     * impacts against workloads in Azure to Microsoft.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<ConnectorInner>, ConnectorInner> beginCreateOrUpdate(String connectorName,
        ConnectorInner resource);

    /**
     * Create a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param resource Resource create parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of a connector is a resource that can be used to proactively report
     * impacts against workloads in Azure to Microsoft.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<ConnectorInner>, ConnectorInner> beginCreateOrUpdate(String connectorName,
        ConnectorInner resource, Context context);

    /**
     * Create a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param resource Resource create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a connector is a resource that can be used to proactively report impacts against workloads in Azure to
     * Microsoft.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ConnectorInner createOrUpdate(String connectorName, ConnectorInner resource);

    /**
     * Create a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param resource Resource create parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a connector is a resource that can be used to proactively report impacts against workloads in Azure to
     * Microsoft.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ConnectorInner createOrUpdate(String connectorName, ConnectorInner resource, Context context);

    /**
     * Update a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param properties The resource properties to be updated.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a connector is a resource that can be used to proactively report impacts against workloads in Azure to
     * Microsoft along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<ConnectorInner> updateWithResponse(String connectorName, ConnectorUpdate properties, Context context);

    /**
     * Update a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param properties The resource properties to be updated.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a connector is a resource that can be used to proactively report impacts against workloads in Azure to
     * Microsoft.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ConnectorInner update(String connectorName, ConnectorUpdate properties);

    /**
     * Delete a Connector.
     * 
     * @param connectorName The name of the connector.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> deleteWithResponse(String connectorName, Context context);

    /**
     * Delete a Connector.
     * 
     * @param connectorName The name of the connector.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void delete(String connectorName);

    /**
     * List Connector resources by subscription ID.
     * 
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a Connector list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<ConnectorInner> list();

    /**
     * List Connector resources by subscription ID.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a Connector list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<ConnectorInner> list(Context context);
}
