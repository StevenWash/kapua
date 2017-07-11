package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.apkinfo.ApkInfo;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;
import org.eclipse.kapua.service.appversion.AppVersionService;


@Api("AppVersions")
@Path("{scopeId}/appversions")
public class AppVersions extends AbstractKapuaResource {

	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final AppVersionService appVersionService = locator.getService(AppVersionService.class);
    
    
    @ApiOperation(value = "Get an AppVersion",  //
    notes = "Returns the AppVersion specified by the \"appVersionId\" path parameter.",  //
    response = AppVersion.class)
	@GET
	@Path("{appVersionId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AppVersion find(
	    @ApiParam(value = "The ScopeId of the requested AppInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
	    @ApiParam(value = "The id of the requested AppInfo", required = true) @PathParam("appVersionId") EntityId appVersionId) {
	
    AppVersion appVersion = null;
	try {
		
	  System.out.println("appVersionId:" + appVersionId);
		appVersion = appVersionService.findById(scopeId, appVersionId);
		
	} catch (Throwable t) {
	    handleException(t);
	}
	return returnNotNullEntity(appVersion);
	}
    
    
    
    @ApiOperation(value = "Get an AppVersion",  //
            notes = "Returns the AppVersion specified by the \"packagename,version\" path parameter.",  //
            response = AppVersion.class)
    @GET
    @Path("update")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AppVersion getAppVersion(
            @ApiParam(value = "The ScopeId of the requested ApiInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The pName of the requested ApiInfo")@QueryParam("package") String pName,
            @ApiParam(value = "The version of the requested ApiInfo")@QueryParam("version") String version
            ) {
    	
    	AppVersion appVersion = null;
   
		if(version==null){
    		version="";
    	}
		
        try {
        	
        	appVersion = appVersionService.findByPackagename(scopeId, pName);
        	
        	if(appVersion==null || appVersion.getVersion().compareTo(version)<=0){
        	   
        		appVersion=appVersionService.findByDistinct(scopeId,pName, version);
        	}
			
        } catch (Exception e) {
        	appVersion=null;
        	System.out.println("appVersion is null");
        }
        return appVersion;
    }
    
    
    @ApiOperation(value = "Get an AppVersion",  //
            notes = "Returns the AppVersion specified by the \"packageName\" path parameter.",  //
            response = AppVersion.class)
    @GET
    @Path("appversions/{packageName}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AppVersion findByPackageName(
            @ApiParam(value = "The ScopeId of the requested AppVersion.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The packageName of the requested AppVersion", required = true) @PathParam("packageName") String packageName) {
    	AppVersion appVersion = null;
        try {
        	
        	appVersion = appVersionService.findByPackagename(scopeId, packageName);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appVersion);

   }
    
    
    /**
     * Creates a new AppVersion based on the information provided in AppVersionCreator
     * parameter.
     *
     * @param scopeId        The {@link ScopeId} in which to create the {@link AppVersion}
     * @param appVersionCreator Provides the information for the new {@link AppVersion} to be created.
     * @return The newly created {@link AppVersion} object.
     */
    @ApiOperation(value = "Create an AppVersion",  //
            notes = "Creates a new AppVersion based on the information provided in AppVersionCreator parameter.",  //
            response = AppVersion.class)
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public AppVersion create(
            @ApiParam(value = "The ScopeId in which to create the AppVersion", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "Provides the information for the new AppVersion to be created", required = true) AppVersionCreator appVersionCreator) {
    	AppVersion appVersion = null;
        try {
        	appVersionCreator.setScopeId(scopeId);
        	System.out.println("appversions-create");
        	appVersion = appVersionService.create(appVersionCreator);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appVersion);
    }
    
    
    /**
     * Deletes the AppVersion specified by the "AppVersionId" path parameter.
     *
     * @param scopeId   The ScopeId of the requested {@link AppVersion}.
     * @param AppVersionId The id of the AppVersion to be deleted.
     * @return HTTP 200 if operation has completed successfully.
     */
    @ApiOperation(value = "Delete an AppVersion",  //
            notes = "Deletes the AppVersion specified by the \"appVersionId\" path parameter.")
    @DELETE
    @Path("{appVersionId}")
    public Response deleteAppVersion(
            @ApiParam(value = "The ScopeId of the Account to delete.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the Account to be deleted", required = true) @PathParam("appVersionId") EntityId appVersionId) {
        try {
            appVersionService.delete(scopeId, appVersionId);
        } catch (Throwable t) {
            handleException(t);
        }
        return Response.ok().build();
    }
    
    
}
