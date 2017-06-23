package org.eclipse.kapua.app.api.v1.resources;

import java.util.ArrayList;
import java.util.List;

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

import net.sf.json.JSONArray;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.weather.BaseIpService;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.weather.internal.IpDomain;
import org.eclipse.kapua.service.weather.internal.NormalResult;
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;
import org.eclipse.kapua.service.weather.internal.SinaIpService;

@Api("Citys")
@Path("/citys") 
public class Citys   extends AbstractKapuaResource  {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WeatherService weatherService = locator.getService(WeatherService.class);
    private static final Domain IP_DOMAIN = new IpDomain();
    
    
    @GET
    @Path("getAllProvince")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets province", //
            notes = "get all  province",
            response=String.class
           )
    public String findProvince() {
           
          List<String> lists=new ArrayList<String>();
    	  String provinceLists=null;
    	
        try {
        	
        	 lists=weatherService.getProvince();
        	
        	 JSONArray jsonArray = JSONArray.fromObject(lists);
        	
        	 provinceLists= jsonArray.toString();
        	
        	
             } catch (Exception e) {
        	  e.printStackTrace();
           }
        return provinceLists;
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
    @GET
    @Path("getCitys/{province}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets the city by province", //
            notes = "Gets the city specified by the province path parameter", //
            response = String.class)
    public String findCityByProvince(
          
            @ApiParam(value = "The province of the requested weather.", required = true) @PathParam("province") String province)//
            {
    	
    	  List<String> lists=new ArrayList<String>();
    	  String  cityLists=null;
    	
        try {
        	
        	lists=weatherService.getCityByProvince(province);
        	
        	JSONArray jsonArray = JSONArray.fromObject(lists);
        	cityLists=jsonArray.toString();
        	
         } catch (Throwable t) {
            handleException(t);
        }
        return cityLists;
    }
    
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
    @Path("getArea")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an area by city", //
            notes = "Gets the Weather.area specified by the city path parameter", //
            response = String.class)
    public String findAreaByCity(
            
            @ApiParam(value = "The city of the requested weather.", required = true) @QueryParam("city") String city)//
            {
    	
    	  List<String>  lists=new ArrayList<String>();
    	  
    	  String areaLists=null;
        try {
        	
        	lists=weatherService.getAreaByCity(city);
        	JSONArray jsonArray = JSONArray.fromObject(lists);
        	areaLists=jsonArray.toString();
        	
         } catch (Throwable t) {
            handleException(t);
        }
        return areaLists;
    }
    
    /**
     * Gets the {@link AccessInfo} specified by the "accessInfoId" path parameter.
     *
     * @param scopeId
     *            The {@link ScopeId} of the requested {@link AccessInfo}.
     * @param accessInfoId
     *            The id of the requested {@link AccessInfo}.
     * @return The requested {@link AccessInfo} object.
     * @throws KapuaException 
     * @since 1.0.0
     */

	
    //getAreaByIp
    @GET
    @Path("getCityByIpAddress/{ipAddress}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an area by IP", //
            notes = "Gets the Area specified by the ip  parameter", //
            response = String.class)
    public String getAreaByIp(
             @ApiParam(value = "The ipAddress of the requested Area", required = true) @PathParam("ipAddress") String ip) throws KapuaException 
            {
    	System.out.println("getAreaByIp<<<<<<<<<<<<<<<");
    	 KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        
		 authorizationService.checkPermission(permissionFactory.newPermission(IP_DOMAIN, Actions.read,  KapuaId.ANY));
    	 
		 NormalResult result = new NormalResult();
		   BaseIpService ipService = new SinaIpService();
		   String city=null;
		   try {
           if(ip!=null&&!ip.equals("")){
        		String httpResult = ipService.getInformation(ip);
				SinaIpInfo ipInfo = new SinaIpInfo();
				ipInfo.doParser(httpResult);
				city = ipInfo.getCity();
				
			   }
         
		   }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
           return city;


     }

}
