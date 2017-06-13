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



import org.eclipse.kapua.service.authorization.access.AccessInfo;
import org.eclipse.kapua.service.authorization.access.AccessInfoListResult;
import org.eclipse.kapua.service.user.User;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherFactory;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.weather.WeatherListResult;
import org.eclipse.kapua.service.weather.internal.WeatherListResultImpl;

import org.eclipse.kapua.service.weather.internal.NormalResult;
import org.eclipse.kapua.service.weather.BaseIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;
import java.util.List;
 





import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("WeatherAPI")
@Path("{scopeId}/weathers") 
public class WeatherAPI extends AbstractKapuaResource {

	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WeatherService weatherService = locator.getService(WeatherService.class);
    private final WeatherFactory weatherFactory = locator.getFactory(WeatherFactory.class);
    
    /**
     * Gets the {@link AccessInfo} specified by the "accessInfoId" path parameter.
     *
     * @param scopeId
     *            The {@link ScopeId} of the requested {@link AccessInfo}.
     * @param accessInfoId
     *            The id of the requested {@link AccessInfo}.
     * @return The requested {@link AccessInfo} object.
     * @since 1.0.0
     */
/*    @GET
    @Path("{weatherId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather", //
            notes = "Gets the Weather specified by the weatherId path parameter", //
            response = Weather.class)
    public Weather find(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the requested Weather", required = true) @PathParam("weatherId") EntityId weatherId) {
        Weather weather = null;
        try {
        	
           weather=weatherService.find(scopeId, weatherId);
           
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weather);
        
        
        
        
    }*/
    
    
    
    /**
     * Gets the  province{@link prinvce} .
     *
     * @param scopeId
     *            The id of the requested {@link Weather}.
     * @return The requested {@link Weather} object.
     * @since 1.0.0
     */
    
    @GET
    @Path("{scopeId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets province", //
            notes = "Gets the Weather.priovince specified by the scopeId path parameter", //
            response = WeatherListResult.class)
    public WeatherListResult findProvince(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId) //
            {
    	
    	
    	WeatherListResult weatherListResult = new WeatherListResultImpl();
        try {
        	
           weatherListResult=weatherService.getProvince(scopeId);
        	
         } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weatherListResult);
    }
    
    
    
    
    /**
     * Gets the city {@link Weather} specified by the "province" path parameter.
     *
     * @param scopeId
     *            The {@link ScopeId} of the requested {@link Weather}.
     * @param province
     *            The id of the requested {@link Weather}.
     * @return The requested {@link Weather} object.
     * @since 1.0.0
     */
/*    @GET
    @Path("{scopeId}/{province}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets the city", //
            notes = "Gets the city specified by the scopeId , province path parameter", //
            response = WeatherListResult.class)
    public WeatherListResult findCityByProvince(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The province of the requested weather.", required = true) @PathParam("province") String province)//
            {
    	
    	
    	WeatherListResult weatherListResult = new WeatherListResultImpl();
        try {
        	
           weatherListResult=weatherService.getCityByProvince(scopeId,province);
        	
         } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weatherListResult);
    }
    */
    
    
    
    
    
    /**
     * Gets the area {@link Weather} specified by the "city" path parameter.
     *
     * @param scopeId
     *            The {@link ScopeId} of the requested {@link Weather}.
     * @param city
     *            The id of the requested {@link Weather}.
     * @return The requested {@link Weather} object.
     * @since 1.0.0
     */
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an area", //
            notes = "Gets the Weather.area specified by the scopeId,city path parameter", //
            response = WeatherListResult.class)
    public WeatherListResult findAreaByCity(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The city of the requested weather.", required = true) @QueryParam("city") String city)//
            {
    	
              WeatherListResult weatherListResult = new WeatherListResultImpl();
        try {
        	
           weatherListResult=weatherService.getAreaByCity(scopeId,city);
        	
         } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weatherListResult);
    }
    
    
    @GET
    @Path("{scopeId}/{area}/{day}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather", //
            notes = "Gets the Weather specified by the weatherId path parameter", //
            response = String.class)
    public String getWeatherByArea(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The id of the requested Weather", required = true) @PathParam("area") String area,//
            @ApiParam(value = "The id of the requested Weather", required = true) @PathParam("day") Integer day) {
        String  weather = null;
        try {
        	
           weather=weatherService.getWeather(area, day);
           
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weather);


     }
    
    //getWeatherByIp
    @GET
    @Path("{ip}/{day}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather", //
            notes = "Gets the Weather specified by the weatherId path parameter", //
            response = String.class)
    public String getWeatherByIp(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The id of the requested Weather", required = true) @PathParam("ip") String ip,//
            @ApiParam(value = "The id of the requested Weather", required = true) @PathParam("day") Integer day) {
		   NormalResult result = new NormalResult();
		   BaseIpService ipService = new SinaIpService();
		   String strResult=null;
        try {
        	if(ip!=null&&!ip.equals("")){
        		String httpResult = ipService.getInformation(ip);
				SinaIpInfo ipInfo = new SinaIpInfo();
				ipInfo.doParser(httpResult);
				
				String city = ipInfo.getCity();
				System.out.println("city>>>>>>"+city);
				String content = weatherService.getWeather(city, day);
				if(content!=null&&!content.equals("")){
					result.setResult(content);
				}else{
					result.setErrcode(1003);
				}
        		
        	}else{
				result.setErrcode(1003);
			}
        	strResult=result.BuildResult();
           
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(strResult);


     }
    
}
