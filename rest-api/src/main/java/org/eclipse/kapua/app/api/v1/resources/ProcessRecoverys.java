package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryCreator;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryService;
import org.eclipse.kapua.service.processrecovery.internal.ProcessRecoveryImpl;

@Api("ProcessRecoverys")
@Path("{scopeId}/processrecoverys")
public class ProcessRecoverys   extends AbstractKapuaResource {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final ProcessRecoveryService processRecoveryService = locator.getService(ProcessRecoveryService.class);
    
    
    @ApiOperation(value = "Get an ProcessRecovery",  //
    	    notes = "Returns the ProcessRecovery specified by the \"processRecoveryId\" path parameter.",  //
    	    response = ProcessRecovery.class)
    		@GET
    		@Path("{processRecoveryId}")
    		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    		public ProcessRecovery find(
    		    @ApiParam(value = "The ScopeId of the requested ProcessRecovery.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
    		    @ApiParam(value = "The id of the requested ProcessRecovery", required = true) @PathParam("processRecoveryId") EntityId processRecoveryId)
    	        {
    		
    	ProcessRecovery processRecovery = null;
    		try {
    			
    			processRecovery = processRecoveryService.findById(scopeId, processRecoveryId);
    			
    		} catch (Throwable t) {
    		    handleException(t);
    		}
    		return returnNotNullEntity(processRecovery);
    		}
    
    
    
    /**
     * Creates a new ProcessRecovery based on the information provided in ProcessRecoveryCreator
     * parameter.
     *
     * @param scopeId        The {@link ScopeId} in which to create the {@link ProcessRecovery}
     * @param processRecoveryCreator Provides the information for the new {@link ProcessRecovery} to be created.
     * @return The newly created {@link ProcessRecovery} object.
     */
    
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Create an ProcessRecovery",  //
    notes = "Creates a new ProcessRecovery based on the information provided in ProcessRecoveryCreator parameter.",  //
    response = ProcessRecovery.class)
    public ProcessRecovery create(
            @ApiParam(value = "The ScopeId in which to create the ProcessRecovery", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "Provides the information for the new ProcessRecovery to be created", required = true) ProcessRecoveryCreator processRecoveryCreator
            ) {
    	ProcessRecovery processRecovery = null;
        try {
        	
        	processRecoveryCreator.setScopeId(scopeId);
        	processRecovery = processRecoveryService.create(processRecoveryCreator);
        	
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(processRecovery);
    }
    
    
    
    /**
     * Deletes the ProcessRecovery specified by the "ProcessRecoveryId" path parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link ProcessRecovery}.
     * @param ProcessRecoveryId The id of the ProcessRecovery to be deleted.
     * @return HTTP 200 if operation has completed successfully.
     */
    @ApiOperation(value = "Delete an ProcessRecovery",  //
            notes = "Deletes the ProcessRecovery specified by the \"processRecoveryId\" path parameter.")
    @DELETE
    @Path("{processRecoveryId}")
    public Response deleteProcessRecovery(
            @ApiParam(value = "The ScopeId of the ProcessRecovery to delete.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the ProcessRecovery to be deleted", required = true) @PathParam("processRecoveryId") EntityId processRecoveryId) {
        try {
        	processRecoveryService.delete(scopeId, processRecoveryId);
        } catch (Throwable t) {
            handleException(t);
        }
        return Response.ok().build();
    }

    
    
    /**
     * Updates the ProcessRecovery based on the information provided in the ProcessRecovery parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link ProcessRecovery}.
     * @param ProcessRecoveryId The id of the requested {@link ProcessRecovery}
     * @param ProcessRecovery   The modified ProcessRecovery whose attributed need to be updated.
     * @return The updated ProcessRecovery.
     */
    @ApiOperation(value = "Update an ProcessRecovery",  //
            notes = "Updates a new ProcessRecovery based on the information provided in the ProcessRecovery parameter.",  //
            response = ProcessRecovery.class)
    @PUT
    @Path("{processRecoveryId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ProcessRecovery update(
            @ApiParam(value = "The ScopeId of the requested ProcessRecovery.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the requested ProcessRecovery", required = true) @PathParam("processRecoveryId") EntityId processRecoveryId, //
            @ApiParam(value = "The modified ProcessRecovery whose attributes needs to be updated", required = true) ProcessRecovery  processRecovery) {
    	System.out.println("ProcessRecovery--update");
    	
    	ProcessRecovery processRecoveryUpdated = null;
        try {
        	
            ((ProcessRecoveryImpl) processRecovery).setScopeId(scopeId);
            processRecovery.setId(processRecoveryId);
          
            processRecoveryUpdated = processRecoveryService.update(processRecovery);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(processRecoveryUpdated);
    }

}
