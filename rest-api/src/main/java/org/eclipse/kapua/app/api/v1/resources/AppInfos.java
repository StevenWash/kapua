package org.eclipse.kapua.app.api.v1.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@Api("AppInfos")
@Path("{scopeId}/appInfos") 
public class AppInfos extends AbstractKapuaResource{
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final AppInfoService appInfoService = locator.getService(AppInfoService.class);
    
    
    @ApiOperation(value = "Get an apkInfo",  //
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
        	System.out.println("scopeId:"+scopeId);
        	appInfo = appInfoService.findByPackagename(scopeId, packageName);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(appInfo);

   }
    
    
    
}
