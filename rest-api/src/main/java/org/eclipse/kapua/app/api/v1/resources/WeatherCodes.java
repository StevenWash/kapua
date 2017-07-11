package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.weather.WeatherCode;
import org.eclipse.kapua.service.weather.WeatherCodeService;


@Api("WeatherCodes")
@Path("/weathercodes")
public class WeatherCodes  extends AbstractKapuaResource {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WeatherCodeService weatherCodeService = locator.getService(WeatherCodeService.class);
    
    
    @GET
    @Path("{cname}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets the WeatherCode by cname", //
            notes = "Gets WeatherCode  specified by the cname path parameter", //
            response = WeatherCode.class)
    public WeatherCode findByCname(
          
            @ApiParam(value = "The code of the requested WeatherCode.") @PathParam("cname") String cname)//
            {
    	
    	WeatherCode  weatherCode=null;
    	System.out.println("cname::::"+cname);
        try {
        	
        	weatherCode=weatherCodeService.findByCname(cname);
        	
            } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weatherCode);
    }
}
