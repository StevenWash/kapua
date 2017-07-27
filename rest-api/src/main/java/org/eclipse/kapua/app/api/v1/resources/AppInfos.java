package org.eclipse.kapua.app.api.v1.resources;

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
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoCreator;
import org.eclipse.kapua.service.appinfo.AppInfoService;
import org.eclipse.kapua.service.appinfo.internal.AppInfoImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@Api("AppInfos")
@Path("{scopeId}/appinfos") 
public class AppInfos extends AbstractKapuaResource{
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final AppInfoService appInfoService = locator.getService(AppInfoService.class);
    
    
    @ApiOperation(value = "Get an appInfo",  //
    notes = "Returns the AppInfo specified by the \"appInfoId\" path parameter.",  //
    response = AppInfo.class)
	@GET
	@Path("{appInfoId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AppInfo find(
	    @ApiParam(value = "The ScopeId of the requested AppInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
	    @ApiParam(value = "The id of the requested AppInfo", required = true) @PathParam("appInfoId") EntityId appInfoId) {
	
	AppInfo appInfo = null;
	try {
	
		appInfo = appInfoService.findById(scopeId, appInfoId);
	} catch (Throwable t) {
	    handleException(t);
	}
	return returnNotNullEntity(appInfo);
	}
    
    
    
    @ApiOperation(value = "Get an AppInfo",  //
            notes = "Returns the AppInfo specified by the \"packageName\" path parameter.",  //
            response = AppInfo.class)
    @GET
    @Path("scopeId/packagename/{packageName}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AppInfo findByPackageName(
            @ApiParam(value = "The ScopeId of the requested AppInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The packageName of the requested AppInfo", required = true) @PathParam("packageName") String packageName) {
    	AppInfo appInfo = null;
        try {
        	
        	appInfo = appInfoService.findByPackagename(scopeId, packageName);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appInfo);

   }
    
    /**
     * Updates the AppInfo based on the information provided in the AppInfo parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link AppInfo}.
     * @param appInfoId The id of the requested {@link AppInfo}
     * @param appInfo   The modified AppInfo whose attributed need to be updated.
     * @return The updated AppInfo.
     */
    @ApiOperation(value = "Update an AppInfo",  //
            notes = "Updates a new AppInfo based on the information provided in the AppInfo parameter.",  //
            response = AppInfo.class)
    @PUT
    @Path("{appInfoId}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public AppInfo update(
            @ApiParam(value = "The ScopeId of the requested AppVersion.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the requested AppInfo", required = true) @PathParam("appInfoId") EntityId appInfoId, //
            @ApiParam(value = "The modified AppInfo whose attributes needs to be updated", required = true) AppInfo  appInfo) {
    	
    	AppInfo appInfoUpdated = null;
        try {
        	
            ((AppInfoImpl) appInfo).setScopeId(scopeId);
            appInfo.setId(appInfoId);
          
            appInfoUpdated = appInfoService.update(appInfo);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appInfoUpdated);
    }
    
    
    
    
    /**
     * Creates a new AppInfo based on the information provided in AppInfoCreator
     * parameter.
     *
     * @param scopeId        The {@link ScopeId} in which to create the {@link AppInfo}
     * @param appInfoCreator Provides the information for the new {@link AppInfo} to be created.
     * @return The newly created {@link AppInfo} object.
     */
    
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Create an AppInfo",  //
    notes = "Creates a new AppInfo based on the information provided in AppInfoCreator parameter.",  //
    response = AppInfo.class)
    public AppInfo create(
            @ApiParam(value = "The ScopeId in which to create the AppInfo", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "Provides the information for the new AppInfo to be created", required = true) AppInfoCreator appInfoCreator) {
    	AppInfo appInfo = null;
        try {
        	
        	appInfoCreator.setScopeId(scopeId);
        	
        	appInfo = appInfoService.create(appInfoCreator);
        	
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appInfo);
    }
    
    
    /**
     * Deletes the AppInfo specified by the "appInfoId" path parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link AppInfo}.
     * @param appInfoId The id of the AppInfo to be deleted.
     * @return HTTP 200 if operation has completed successfully.
     */
    @ApiOperation(value = "Delete an AppInfo",  //
            notes = "Deletes the AppInfo specified by the \"appInfoId\" path parameter.")
    @DELETE
    @Path("{appInfoId}")
    public Response deleteAppVersion(
            @ApiParam(value = "The ScopeId of the Account to delete.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the Account to be deleted", required = true) @PathParam("appInfoId") EntityId appInfoId) {
        try {
            appInfoService.delete(scopeId, appInfoId);
        } catch (Throwable t) {
            handleException(t);
        }
        return Response.ok().build();
    }
    
}
