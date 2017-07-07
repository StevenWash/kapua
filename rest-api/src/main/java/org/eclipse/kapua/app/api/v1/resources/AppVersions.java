package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.apkinfo.ApkInfo;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionService;


@Api("AppVersions")
@Path("{scopeId}/appVersions")
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
    @Path("appVersions/{packageName}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AppVersion findByPackageName(
            @ApiParam(value = "The ScopeId of the requested AppVersion.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The packageName of the requested AppVersion", required = true) @PathParam("packageName") String packageName) {
    	AppVersion appVersion = null;
        try {
        	System.out.println("scopeId:"+scopeId);
        	System.out.println("packagename::::///"+packageName);
        	appVersion = appVersionService.findByPackagename(scopeId, packageName);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appVersion);

   }
}
