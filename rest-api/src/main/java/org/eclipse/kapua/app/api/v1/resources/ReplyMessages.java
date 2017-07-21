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
import org.eclipse.kapua.service.replymessage.ReplyMessage;
import org.eclipse.kapua.service.replymessage.ReplyMessageCreator;
import org.eclipse.kapua.service.replymessage.ReplyMessageService;
import org.eclipse.kapua.service.replymessage.internal.ReplyMessageImpl;

@Api("ReplyMessages")
@Path("{scopeId}/replymessages")
public class ReplyMessages extends AbstractKapuaResource {

	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final ReplyMessageService replyMessageService = locator.getService(ReplyMessageService.class);
    
    
    @ApiOperation(value = "Get an ReplyMessage",  //
    notes = "Returns the ReplyMessage specified by the \"replyMessageId\" path parameter.",  //
    response = ReplyMessage.class)
	@GET
	@Path("{replyMessageId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ReplyMessage find(
	    @ApiParam(value = "The ScopeId of the requested ReplyMessage.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
	    @ApiParam(value = "The id of the requested ReplyMessage", required = true) @PathParam("replyMessageId") EntityId replyMessageId)
        {
	
    	ReplyMessage replyMessage = null;
	try {
		
	  replyMessage = replyMessageService.findById(scopeId, replyMessageId);
		
	} catch (Throwable t) {
	    handleException(t);
	}
	return returnNotNullEntity(replyMessage);
	}
    
    
    @POST
    @Path("create/{processrecoveryId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Create an ReplyMessage",  //
    notes = "Creates a new ReplyMessage based on the information provided in ReplyMessageCreator parameter.",  //
    response = ReplyMessage.class)
    public ReplyMessage create(
            @ApiParam(value = "The ScopeId in which to create the ReplyMessage", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, 
            @ApiParam(value = "Provides the information for the new ReplyMessage to be created", required = true)  @PathParam("processrecoveryId") EntityId processrecoveryId,
            @ApiParam(value = "Provides the information for the new ReplyMessage to be created", required = true) ReplyMessageCreator replyMessageCreator
            
            ) {
    	
    	ReplyMessage replyMessage = null;
        try {
        	
        	replyMessageCreator.setScopeId(scopeId);
        	replyMessageCreator.setProcessrecoveryId(processrecoveryId);
        	System.out.println("replyMessage-create");
        	replyMessage = replyMessageService.create(replyMessageCreator);
        	
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(replyMessage);
    }
    
    
    
    /**
     * Deletes the ReplyMessage specified by the "ReplyMessageId" path parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link ReplyMessage}.
     * @param ReplyMessageId The id of the ReplyMessage to be deleted.
     * @return HTTP 200 if operation has completed successfully.
     */
    @ApiOperation(value = "Delete an ReplyMessage",  //
            notes = "Deletes the ReplyMessage specified by the \"replyMessageId\" path parameter.")
    @DELETE
    @Path("{replyMessageId}")
    public Response deleteReplyMessage(
            @ApiParam(value = "The ScopeId of the Account to delete.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the ReplyMessage to be deleted", required = true) @PathParam("replyMessageId") EntityId replyMessageId) {
        try {
            replyMessageService.delete(scopeId, replyMessageId);
        } catch (Throwable t) {
            handleException(t);
        }
        return Response.ok().build();
    }
    
    
    
    
    /**
     * Updates the ReplyMessage based on the information provided in the ReplyMessage parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link ReplyMessage}.
     * @param ReplyMessageId The id of the requested {@link ReplyMessage}
     * @param ReplyMessage   The modified ReplyMessage whose attributed need to be updated.
     * @return The updated ReplyMessage.
     */
    @ApiOperation(value = "Update an ReplyMessage",  //
            notes = "Updates a new ReplyMessage based on the information provided in the ReplyMessage parameter.",  //
            response = ReplyMessage.class)
    @PUT
    @Path("{replyMessageId}/{processrecoveryId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ReplyMessage update(
            @ApiParam(value = "The ScopeId of the requested ReplyMessage.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the requested ReplyMessage", required = true) @PathParam("replyMessageId") EntityId replyMessageId, //
            @ApiParam(value = "The processrecoveryId of the requested ReplyMessage", required = true) @PathParam("processrecoveryId") EntityId processrecoveryId, //
            @ApiParam(value = "The modified ReplyMessage whose attributes needs to be updated", required = true) ReplyMessage  replyMessage) {
    	
    	ReplyMessage replyMessageUpdated = null;
        try {
        	System.out.println("scopeId:::::::::"+scopeId);
            ((ReplyMessageImpl) replyMessage).setScopeId(scopeId);
            
            replyMessage.setId(replyMessageId);
            replyMessage.setProcessrecoveryId(processrecoveryId);
            replyMessageUpdated = replyMessageService.update(replyMessage);
            
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(replyMessageUpdated);
    }
  
}
