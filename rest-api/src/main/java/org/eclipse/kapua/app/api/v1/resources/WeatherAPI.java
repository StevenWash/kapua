package org.eclipse.kapua.app.api.v1.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.weather.internal.GeoIPv4;
import org.eclipse.kapua.service.weather.internal.NormalResult;
import org.eclipse.kapua.service.weather.BaseIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;
import org.eclipse.kapua.service.weather.internal.WeatherPresentation;
import org.eclipse.kapua.service.weather.internal.YahooWeatherService;
import org.eclipse.kapua.service.weather.internal.Channel;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("WeatherAPI")
@Path("{scopeId}/weathers") 
public class WeatherAPI extends AbstractKapuaResource {

	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WeatherService weatherService = locator.getService(WeatherService.class);
    
    
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
            response = List.class)
    public List<String> findProvince(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId) //
            {
    	System.out.println("weatherService:::::::::::<<<<<<<<<<<<<");
    	System.out.println("weatherService:::::::::::"+weatherService);
    	List<String> provinceLists=new ArrayList<String>();
    	
        try {
        	
        	provinceLists=weatherService.getProvince(scopeId);
        	
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
    @Path("getCitys/{scopeId}/{province}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets the city by province", //
            notes = "Gets the city specified by the scopeId , province path parameter", //
            response = List.class)
    public List<String> findCityByProvince(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The province of the requested weather.", required = true) @PathParam("province") String province)//
            {
    	
    	List<String> cityLists=new ArrayList<String>();
    	
        try {
        	
        	cityLists=weatherService.getCityByProvince(scopeId,province);
        	
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
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an area by city", //
            notes = "Gets the Weather.area specified by the scopeId,city path parameter", //
            response = List.class)
    public List<String> findAreaByCity(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The city of the requested weather.", required = true) @QueryParam("city") String city)//
            {
    	
    	List<String> areaLists=new ArrayList<String>();
        try {
        	
        	areaLists=weatherService.getAreaByCity(scopeId,city);
           
        	
         } catch (Throwable t) {
            handleException(t);
        }
        return areaLists;
    }
    
    //**Sina
    @GET
    @Path("{scopeId}/{city}/{day}")    
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather by area and day", //
            notes = "Gets the Weather specified by the area,day path parameter", //
            response = String.class)
    public String getWeatherByArea(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The city of the requested Weather", required = true) @PathParam("city") String city,//
            @ApiParam(value = "The day of the requested Weather", required = true, defaultValue ="0")@DefaultValue("0") @PathParam("day") Integer day) {
        String  weather = null;
        try {
        	
           weather=weatherService.getWeather(city, day);
           
           
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(weather);


     }
    
    //getWeatherByIp  **Sina
    @GET
    @Path("{ip}/{day}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather by ip  and day ", //
            notes = "Gets the Weather specified by the weatherId path parameter", //
            response = String.class)
    public String getWeatherByIp(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The ip of the requested Weather", required = true) @PathParam("ip") String ip,//
            @ApiParam(value = "The day of the requested Weather", required = true, defaultValue ="0") @DefaultValue("0") @PathParam("day") Integer day) {
		   NormalResult result = new NormalResult();
		   BaseIpService ipService = new SinaIpService();
		   String strResult=null;
        try {
        	if(ip!=null&&!ip.equals("")){
        		String httpResult = ipService.getInformation(ip);
				SinaIpInfo ipInfo = new SinaIpInfo();
				ipInfo.doParser(httpResult);
				String city = ipInfo.getCity();
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
    
    
    @GET
    @Path("YaHooApi/{area}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an Weather by YaHooWeatherApi and area", //
            notes = "Gets the Weather specified by the area path parameter", //
            response = String.class)
    public String getWeathersByArea(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The area of the requested Weather", required = true) @PathParam("area") String area)
            {
    	StringBuffer buffer =new StringBuffer();
    	YahooWeatherService service = null;
        String  weathers = null;
        try {
        	service=new YahooWeatherService();
        	Channel channel = service.getForecastForLocation(area).first(3).get(0);
        	buffer.append(channel);
        /*	List<Forecast> list=channel.getItem().getForecasts();
    		for(Forecast fo:list){
    			builder.append("day: "+fo.getDay()+"  date:"+fo.getDate()+"  high:"+fo.getHigh()+" low:"+fo.getLow()+"  code:"+fo.getCode()+"     text:"+fo.getText()+"\n");
    		}*/
        	buffer.append(channel.getItem().getForecasts().get(0));
        	weathers=buffer.toString();
        	
          
        } catch (Throwable t) {
            handleException(t);
        }
        return weathers;


     }
    
    
    @GET
    @Path("{scopeId}/YaHooApi/{ip}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an Weather by YaHooWeatherApi and ipAddress,scopeId", //
            notes = "Gets the Weather specified by the ipAddress path parameter", //
            response = String.class)
    public String getWeathersByIpAddress(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The ipAddress of the requested Weather", required = true) @PathParam("ip") String ip)
            {
    	StringBuffer buffer =new StringBuffer(); 
    	YahooWeatherService service = null;
        
        String weather=null;
        try {
        	String area=GeoIPv4.getLocation(InetAddress.getByName(ip)).getCity();
        	
        	
        	service=new YahooWeatherService();
        	Channel channel = service.getForecastForLocation(area).first(3).get(0);
        	
        	buffer.append(channel);
        	/*List<Forecast> list=channel.getItem().getForecasts();
    		for(Forecast fo:list){
    			builder.append("day: "+fo.getDay()+"  date:"+fo.getDate()+"  high:"+fo.getHigh()+" low:"+fo.getLow()+"  code:"+fo.getCode()+"     text:"+fo.getText()+"\n");
    		}*/
        	buffer.append(channel.getItem().getForecasts().get(0));
        	
        	
        	weather=buffer.toString();
           
        } catch (Throwable t) {
            handleException(t);
        }
        return weather;


     }
    
    
    @GET
    @Path("{scopeId}/weathers/{weatherApiName}/{ipAddress}/{day}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    @ApiOperation(value = "Gets an Weather by day, ipAddress,weatherApiName", //
            notes = "Gets the Weather specified by the day,scopeId,weatherApiName,ipAddress path parameter", //
            response = String.class)
    public String getWeatherByApi(
            @ApiParam(value = "The ScopeId of the requested Weather.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The weatherApiName of the requested Weather", required = true) @PathParam("weatherApiName") String  weatherApiName,
            @ApiParam(value = "The ipAddress of the requested Weather", required = true) @PathParam("ipAddress") String ipAddress,
            @ApiParam(value = "The day of the requested Weather", required = true, defaultValue ="0") @DefaultValue("0") @PathParam("day") Integer day){
        String weatherInfo=null;
        
        try {
        	 if(weatherApiName.equals("sina")){
        		 String content= this.getWeatherByIp(scopeId, ipAddress, day);
        		 
        		  WeatherPresentation weatherPresentation=new WeatherPresentation();
        	      
        	      
        	 
        		 
        		  JSONObject myjObject = new JSONObject(content);
        		 
        		  weatherPresentation.setDate(myjObject.getString("savedate_zhishu"));
     	         weatherPresentation.setLocation(myjObject.getString("city"));
     	         weatherPresentation.setHigh(myjObject.getString("temperature1")); 
     	         weatherPresentation.setLow(myjObject.getString("temperature2"));
     	         weatherPresentation.setText(myjObject.getString("yd_s"));
        	      
        	  
        		    weatherInfo=weatherPresentation.toString();
        	 }else if(weatherApiName.equals("yahoo")){
        		 weatherInfo=this.getWeathersByIpAddress(scopeId, ipAddress);
        	 }else{
        		 weatherInfo=null;
        	 }
         
          
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return weatherInfo;
        
   }
    
}
