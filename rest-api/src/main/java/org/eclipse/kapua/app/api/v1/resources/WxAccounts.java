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
import org.eclipse.kapua.service.wxaccount.WxAccount;
import org.eclipse.kapua.service.wxaccount.WxAccountCreator;
import org.eclipse.kapua.service.wxaccount.WxAccountService;
import org.eclipse.kapua.service.wxaccount.internal.WxAccountImpl;

@Api("WxAccounts")
@Path("{scopeId}/wxaccounts")
public class WxAccounts extends AbstractKapuaResource {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WxAccountService wxAccountService = locator.getService(WxAccountService.class);
    
    
    @ApiOperation(value = "Get an WxAccount",  //
    notes = "Returns the WxAccount specified by the \"wxAccountId\" path parameter.",  //
    response = WxAccount.class)
	@GET
	@Path("{wxAccountId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public WxAccount find(
	    @ApiParam(value = "The ScopeId of the requested WxAccount.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
	    @ApiParam(value = "The id of the requested WxAccount", required = true) @PathParam("wxAccountId") EntityId wxAccountId)
        {
    	WxAccount wxAccount = null;
	try {
		
	  System.out.println("wxAccountId:" + wxAccountId);
	  wxAccount = wxAccountService.findById(scopeId, wxAccountId);
		
	} catch (Throwable t) {
	    handleException(t);
	}
	return returnNotNullEntity(wxAccount);
	}
    
    
    
    /**
     * Creates a new WxAccount based on the information provided in WxAccountCreator
     * parameter.
     *
     * @param scopeId        The {@link ScopeId} in which to create the {@link WxAccount}
     * @param wxAccountCreator Provides the information for the new {@link WxAccount} to be created.
     * @return The newly created {@link WxAccount} object.
     */
    
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Create an WxAccount",  //
    notes = "Creates a new WxAccount based on the information provided in WxAccountCreator parameter.",  //
    response = WxAccount.class)
    public WxAccount create(
            @ApiParam(value = "The ScopeId in which to create the WxAccount", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "Provides the information for the new WxAccount to be created", required = true) WxAccountCreator wxAccountCreator) {
    	WxAccount wxAccount = null;
        try {
        	
        	wxAccountCreator.setScopeId(scopeId);
        	wxAccount = wxAccountService.create(wxAccountCreator);
        	
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(wxAccount);
    }
    
    
    
    
    /**
     * Deletes the WxAccount specified by the "WxAccountId" path parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link WxAccount}.
     * @param WxAccountId The id of the WxAccount to be deleted.
     * @return HTTP 200 if operation has completed successfully.
     */
    @ApiOperation(value = "Delete an WxAccount",  //
            notes = "Deletes the WxAccount specified by the \"wxAccountId\" path parameter.")
    @DELETE
    @Path("{wxAccountId}")
    public Response deleteWxAccount(
            @ApiParam(value = "The ScopeId of the WxAccount to delete.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the WxAccount to be deleted", required = true) @PathParam("wxAccountId") EntityId wxAccountId) {
        try {
            wxAccountService.delete(scopeId, wxAccountId);
        } catch (Throwable t) {
            handleException(t);
        }
        return Response.ok().build();
    }

    
    
    /**
     * Updates the WxAccount based on the information provided in the WxAccount parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link WxAccount}.
     * @param wxAccountId The id of the requested {@link WxAccount}
     * @param wxAccount   The modified WxAccount whose attributed need to be updated.
     * @return The updated wxAccount.
     */
    @ApiOperation(value = "Update an WxAccount",  //
            notes = "Updates a new WxAccount based on the information provided in the WxAccount parameter.",  //
            response = WxAccount.class)
    @PUT
    @Path("{wxAccountId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public WxAccount update(
            @ApiParam(value = "The ScopeId of the requested AppVersion.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the requested AppVersion", required = true) @PathParam("wxAccountId") EntityId wxAccountId, //
            @ApiParam(value = "The modified AppVersion whose attributes needs to be updated", required = true) WxAccount  wxAccount) {
    	System.out.println("WxAccount--update");
    	
    	WxAccount wxAccountUpdated = null;
        try {
        	
            ((WxAccountImpl) wxAccount).setScopeId(scopeId);
            wxAccount.setId(wxAccountId);
          
            wxAccountUpdated = wxAccountService.update(wxAccount);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(wxAccountUpdated);
    }
}
