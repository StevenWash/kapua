package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
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
}
