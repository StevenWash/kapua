/*******************************************************************************
 * Copyright (c) 2011, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.v1.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.kapua.app.api.v1.resources.model.CountResult;
import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.commons.model.query.predicate.AndPredicate;
import org.eclipse.kapua.commons.model.query.predicate.AttributePredicate;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.model.query.predicate.KapuaAttributePredicate.Operator;
import org.eclipse.kapua.service.authorization.role.Role;
import org.eclipse.kapua.service.authorization.role.RoleCreator;
import org.eclipse.kapua.service.authorization.role.RoleFactory;
import org.eclipse.kapua.service.authorization.role.RoleListResult;
import org.eclipse.kapua.service.authorization.role.RoleQuery;
import org.eclipse.kapua.service.authorization.role.RoleService;
import org.eclipse.kapua.service.authorization.role.shiro.RoleImpl;
import org.eclipse.kapua.service.authorization.role.shiro.RolePredicates;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Roles")
@Path("{scopeId}/roles")
public class Roles extends AbstractKapuaResource {

    private final KapuaLocator locator = KapuaLocator.getInstance();
    private final RoleService roleService = locator.getService(RoleService.class);
    private final RoleFactory roleFactory = locator.getFactory(RoleFactory.class);

    /**
     * Gets the {@link Role} list in the scope.
     *
     * @param scopeId The {@link ScopeId} in which to search results.
     * @param name    The {@link Role} name in which to search results.
     * @param offset  The result set offset.
     * @param limit   The result set limit.
     * @return The {@link RoleListResult} of all the roles associated to the current selected scope.
     * @since 1.0.0
     */
    @ApiOperation(value = "Gets the Role list in the scope",
            notes = "Returns the list of all the roles associated to the current selected scope.",
            response = Role.class,
            responseContainer = "RoleListResult")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RoleListResult simpleQuery(
            @ApiParam(value = "The ScopeId in which to search results.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The role name to filter results.") @QueryParam("name") String name,
            @ApiParam(value = "The result set offset.", defaultValue = "0") @QueryParam("offset") @DefaultValue("0") int offset,
            @ApiParam(value = "The result set limit.", defaultValue = "50") @QueryParam("limit") @DefaultValue("50") int limit) {
        RoleListResult roleListResult = roleFactory.newListResult();
        try {
            RoleQuery query = roleFactory.newQuery(scopeId);

            AndPredicate andPredicate = new AndPredicate();
            if (!Strings.isNullOrEmpty(name)) {
                andPredicate.and(new AttributePredicate<>(RolePredicates.NAME, name,Operator.LIKE));
            }
            query.setPredicate(andPredicate);

            query.setOffset(offset);
            query.setLimit(limit);

            roleListResult = query(scopeId, query);
        } catch (Throwable t) {
            handleException(t);
        }
        return roleListResult;
    }

    /**
     * Queries the results with the given {@link RoleQuery} parameter.
     *
     * @param scopeId The {@link ScopeId} in which to search results.
     * @param query   The {@link RoleQuery} to use to filter results.
     * @return The {@link RoleListResult} of all the result matching the given {@link RoleQuery} parameter.
     * @since 1.0.0
     */
    @ApiOperation(value = "Queries the Roles",
            notes = "Queries the Roles with the given RoleQuery parameter returning all matching Roles",
            response = Role.class,
            responseContainer = "RoleListResult")
    @POST
    @Path("_query")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public RoleListResult query(
            @ApiParam(value = "The ScopeId in which to search results.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The RoleQuery to use to filter results.", required = true) RoleQuery query) {
        RoleListResult roleListResult = null;
        try {
            query.setScopeId(scopeId);
            roleListResult = roleService.query(query);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(roleListResult);
    }

    /**
     * Counts the results with the given {@link RoleQuery} parameter.
     *
     * @param scopeId The {@link ScopeId} in which to search results.
     * @param query   The {@link RoleQuery} to use to filter results.
     * @return The count of all the result matching the given {@link RoleQuery} parameter.
     * @since 1.0.0
     */
    @ApiOperation(value = "Counts the Roles",
            notes = "Counts the Roles with the given RoleQuery parameter returning the number of matching Roles",
            response = CountResult.class)
    @POST
    @Path("_count")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public CountResult count(
            @ApiParam(value = "The ScopeId in which to count results", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The RoleQuery to use to filter count results", required = true) RoleQuery query) {
        CountResult countResult = null;
        try {
            query.setScopeId(scopeId);
            countResult = new CountResult(roleService.count(query));
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(countResult);
    }

    /**
     * Creates a new Role based on the information provided in RoleCreator
     * parameter.
     *
     * @param scopeId     The {@link ScopeId} in which to create the {@link Role}
     * @param roleCreator Provides the information for the new {@link Role} to be created.
     * @return The newly created {@link Role} object.
     */
    @ApiOperation(value = "Create a Role", notes = "Creates a new Role based on the information provided in RoleCreator parameter.", response = Role.class)
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Role create(
            @ApiParam(value = "The ScopeId in which to create the Account", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "Provides the information for the new Role to be created", required = true) RoleCreator roleCreator) {
        Role role = null;
        try {
            roleCreator.setScopeId(scopeId);
            role = roleService.create(roleCreator);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(role);
    }

    /**
     * Returns the Role specified by the "roleId" path parameter.
     *
     * @param scopeId The {@link ScopeId} of the requested {@link Role}.
     * @param roleId  The id of the requested {@link Role}.
     * @return The requested {@link Role} object.
     */
    @ApiOperation(value = "Get a Role", notes = "Returns the Role specified by the \"roleId\" path parameter.", response = Role.class)
    @GET
    @Path("{roleId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Role find(
            @ApiParam(value = "The ScopeId of the requested Account.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The id of the requested Role", required = true) @PathParam("roleId") EntityId roleId) {
        Role role = null;
        try {
            role = roleService.find(scopeId, roleId);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(role);
    }

    /**
     * Updates the Role based on the information provided in the Role parameter.
     *
     * @param scopeId The ScopeId of the requested {@link Role}.
     * @param roleId  The id of the requested {@link Role}
     * @param role    The modified Role whose attributed need to be updated.
     * @return The updated {@link Role}.
     */
    @ApiOperation(value = "Update an Role", notes = "Updates a new Role based on the information provided in the Role parameter.", response = Role.class)
    @PUT
    @Path("{roleId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Role update(
            @ApiParam(value = "The ScopeId of the requested Account.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The id of the requested Role", required = true) @PathParam("roleId") EntityId roleId,
            @ApiParam(value = "The modified Role whose attributed need to be updated", required = true) Role role) {
        Role roleUpdated = null;
        try {
            ((RoleImpl) role).setScopeId(scopeId);
            role.setId(roleId);

            roleUpdated = roleService.update(role);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(roleUpdated);
    }

    /**
     * Deletes the Role specified by the "roleId" path parameter.
     *
     * @param scopeId The ScopeId of the requested {@link Role}.
     * @param roleId  The id of the Role to be deleted.
     * @return HTTP 200 if operation has completed successfully.
     */
    @ApiOperation(value = "Delete a Role", notes = "Deletes the Role specified by the \"roleId\" path parameter.")
    @DELETE
    @Path("{roleId}")
    public Response deleteRole(
            @ApiParam(value = "The ScopeId of the Account to delete.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The id of the Role to be deleted", required = true) @PathParam("roleId") EntityId roleId) {
        try {
            roleService.delete(scopeId, roleId);
        } catch (Throwable t) {
            handleException(t);
        }
        return Response.ok().build();
    }
    
    

 /* @ApiOperation(value = "Gets the Role list by scopeId,accessInfo.userId",
            notes = "Returns the list of all the roles associated to the current selected scope.",
            response = String.class
            )
    @GET
    @Path("query/{scopeId}/{accessInfoUserId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String getQuery(
            @ApiParam(value = "The ScopeId in which to search results.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId)
        //    @ApiParam(value = "The accessInfoUserId  to search") @PathParam("accessInfoUserId") EntityId accessInfoUserId)
             {
             String result = null;
        try {
           
        result=roleService.queryRole(scopeId);
            
       } catch (Throwable t) {
            handleException(t);
        }
           return result;
    } */

}
