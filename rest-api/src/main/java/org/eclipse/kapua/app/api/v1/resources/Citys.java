package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.app.common.util.IpHelper;
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
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;
import org.eclipse.kapua.service.weather.internal.SinaIpService;
import org.eclipse.kapua.service.weather.util.ErrorMessageException;

@Api("Citys")
@Path("/citys") 
public class Citys   extends AbstractKapuaResource  {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WeatherService weatherService = locator.getService(WeatherService.class);
    private static final Domain IP_DOMAIN = new IpDomain();
    @Context HttpServletRequest request;
    
    
    @GET
    @Path("allProvince")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets province", //
            notes = "get all  province",
            response=String.class
           )
    public String findProvince() {
           
         
    	  String provinceLists=null;
    	
        try {
        	
        	provinceLists=weatherService.getProvince();
        	
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
    @Path("citys/{province}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets the city by province", //
            notes = "Gets the city specified by the province path parameter", //
            response = String.class)
    public String findCityByProvince(
          
            @ApiParam(value = "The province of the requested weather.", required = true) @PathParam("province") String province)//
            {
    	     String  cityLists=null;
    	
        try {
        	
        	cityLists=weatherService.getCityByProvince(province);
        	
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
    @Path("area")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an area by city", //
            notes = "Gets the Weather.area specified by the city path parameter", //
            response = String.class)
    public String findAreaByCity(
            
            @ApiParam(value = "The city of the requested weather.", required = true) @QueryParam("city") String city)//
            {
    	
    	    String areaLists=null;
        try {
        	
        	areaLists=weatherService.getAreaByCity(city);
        
        	
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
    @Path("cityByIpAddress/ipAddress")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an city by ipAddress", //
            notes = "Gets the city specified by the ip  parameter", //
            response = String.class)
    public String getCityByIp(
             @ApiParam(value = "The ipAddress of the requested Area") @QueryParam("ipAddress") String ip) throws KapuaException 
            {
    	
    	 KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        
		 authorizationService.checkPermission(permissionFactory.newPermission(IP_DOMAIN, Actions.read,  KapuaId.ANY));
    	 
		 BaseIpService ipService = new SinaIpService();
		 String strResult=null;
		
		 try {
			//boolean flag=IpHelper.isIpv4(ip);
			if(ip==null||ip.equals("")||IpHelper.isIpv4(ip)==false){//自动获取Ip
				 ip = request.getRemoteHost();
				 String httpResult = ipService.getInformation(ip);
				 SinaIpInfo ipInfo = new SinaIpInfo();
				 ipInfo.doParser(httpResult);
				 strResult = ipInfo.getCity();
			}else{
				String httpResult = ipService.getInformation(ip);
        		SinaIpInfo ipInfo = new SinaIpInfo();
				ipInfo.doParser(httpResult);
				strResult= ipInfo.getCity();
			}
			
		}catch(ErrorMessageException mes){
			strResult=mes.warnMess();
        	System.out.println("city is null");
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         return strResult;
     }

}
