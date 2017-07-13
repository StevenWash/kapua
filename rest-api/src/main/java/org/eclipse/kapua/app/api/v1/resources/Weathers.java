package org.eclipse.kapua.app.api.v1.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.eclipse.kapua.app.common.util.IpHelper;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.weather.YahooWeather;
import org.eclipse.kapua.service.weather.internal.GeoIPv4;
import org.eclipse.kapua.service.weather.internal.NormalResult;
import org.eclipse.kapua.service.weather.BaseIpService;
import org.eclipse.kapua.service.weather.internal.Atmosphere;
import org.eclipse.kapua.service.weather.Forecast;
import org.eclipse.kapua.service.weather.internal.SinaIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;
import org.eclipse.kapua.service.weather.internal.WeatherPresentation;
import org.eclipse.kapua.service.weather.internal.Wind;
import org.eclipse.kapua.service.weather.internal.Location;
import org.eclipse.kapua.service.weather.internal.YahooWeatherImpl;
import org.eclipse.kapua.service.weather.internal.YahooWeatherService;
import org.eclipse.kapua.service.weather.internal.Channel;
import org.eclipse.kapua.service.weather.util.ErrorMessageException;
import org.json.JSONException;







import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Weathers")
@Path("weathers") 
public class Weathers extends AbstractKapuaResource {

	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final WeatherService weatherService = locator.getService(WeatherService.class);
    
    @Context HttpServletRequest request;
    
  
 
    
    //**Sina
    @GET
    @Path("sina/{city}/{day}")    
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather by area and day", //
            notes = "Gets the Weather specified by the area,day path parameter", //
            response = String.class)
    public String getWeatherByArea(
          
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
    @Path("sina/ip/{day}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather by ip  and day ", //
            notes = "Gets the Weather specified by the weatherId path parameter", //
            response = String.class)
    public String getWeatherByIp(
           @ApiParam(value = "The ip of the requested Weather") @QueryParam("ipAddress") String ip,
           @ApiParam(value = "The day of the requested Weather", required = true, defaultValue ="0") @DefaultValue("0") @PathParam("day") Integer day)throws Exception {
		   NormalResult result = new NormalResult();
		   BaseIpService ipService = new SinaIpService();
		   String strResult=null;
       try {
    	   
   
        //	boolean flag=IpHelper.isIpv4(ip); 
        	if(ip==null||ip.equals("")||IpHelper.isIpv4(ip)==false){//自动获取Ip
        		      
        	         ip = request.getRemoteHost();  
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
					    strResult=result.BuildResult();
					
			}else{
        		String httpResult = ipService.getInformation(ip);
        		SinaIpInfo ipInfo = new SinaIpInfo();
				ipInfo.doParser(httpResult);
				String city = ipInfo.getCity();
			
				    String content = weatherService.getWeather(city, day);
						if(content!=null&&!content.equals("")){
							result.setResult(content);
				
					strResult=result.BuildResult();
        	          }
        	
        	}
        	
           
        }catch(ErrorMessageException mes){
        	System.out.println("city is null");
        	strResult=mes.warnMess();
        }catch (Exception e) {
        	e.printStackTrace();
          
        }
       
			 return strResult;
        	
   } 
        	

  
    
    @GET
    @Path("yahooapi/{city}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather by YaHooWeatherApi and area", //
            notes = "Gets the Weather specified by the area city parameter", //
            response = YahooWeather.class)
    public YahooWeather getWeathersByArea(
          
            @ApiParam(value = "The area of the requested Weather", required = true) @PathParam("city") String city)
            {
    	
    	YahooWeatherService service = null;
       
        YahooWeather yhWeather=null;
        try {
        	service=new YahooWeatherService();
        	Channel channel = service.getForecastForLocation(city).first(3).get(0);
        
        	
        	//空气湿度
        	Integer humidity=channel.getAtmosphere().getHumidity();
        	Atmosphere atmosphere =new Atmosphere(humidity);
        	JSONObject  atmo = JSONObject.fromObject(atmosphere);
        		
        	
        	//风速 风向
        	Float speed=channel.getWind().getSpeed();
        	Integer direction=channel.getWind().getDirection();
        	Wind wind=new Wind(direction,speed);
        	JSONObject  wind_speed = JSONObject.fromObject(wind);
        	
        	//地理位置
        	Location location=channel.getLocation();
        	JSONObject  jsonLocation = JSONObject.fromObject(location);
        	
        	//发布时间
        	JSONObject  chanelJson = JSONObject.fromObject(channel);
        	
        	List<Forecast> forecastList=new ArrayList<Forecast>();
        	List<Forecast> forecasts=channel.getItem().getForecasts();
        	
            for(int i=0;i<=2;i++){
            	forecastList.add(forecasts.get(i));
            }
        
        	JSONObject  forecastJson = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
        	JSONObject jsonThree = new JSONObject();
        	
        	jsonThree.putAll(wind_speed);
        	jsonThree.putAll(atmo);//湿度
        	jsonThree.putAll(jsonLocation);
        	jsonThree.putAll(forecastJson);
            jsonThree.putAll(chanelJson);
        	
        	JSONObject jsonObject = JSONObject.fromObject(jsonThree);
        	yhWeather=new YahooWeatherImpl();
            yhWeather.setCity(jsonObject.getString("city"));
        	
        	yhWeather.setCountry(jsonObject.getString("country"));
        	
        	yhWeather.setRegion(jsonObject.getString("region"));
        	
        	/*yhWeather.setDay(jsonObject.getString("day"));
        	
        	yhWeather.setDate(jsonObject.getString("date"));
        
        	yhWeather.setHigh_temp(jsonObject.getString("high"));
        	
        	yhWeather.setLow_temp(jsonObject.getString("low"));
        	
        	yhWeather.setText(jsonObject.getString("text"));*/
        	
        	yhWeather.setHumidity(jsonObject.getString("humidity"));
        	  
        	yhWeather.setWind_speed(jsonObject.getString("speed"));
        	
        	yhWeather.setDirection(jsonObject.getString("direction"));
        	
        	//yhWeather.setPubdate(jsonObject.getString("lastBuildDate"));
        	
        	
        	yhWeather.addItems(forecastList);
        	
        	
          
        } catch (Exception e) {
           e.printStackTrace();
        }
        return yhWeather;


     }
    
    
    @GET
    @Path("yahooapi/ip")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
    @ApiOperation(value = "Gets an Weather by YaHooWeatherApi and ipAddress,scopeId", //
            notes = "Gets the Weather specified by the ipAddress path parameter", //
            response = YahooWeather.class)
    public YahooWeather getWeathersByIpAddress(
           
            @ApiParam(value = "The ipAddress of the requested Weather") @QueryParam("ipAddress") String ip){
            
    	StringBuffer buffer =new StringBuffer(); 
    	YahooWeatherService service = null;
        String weather=null;
        YahooWeather yhWeather=null;
        try {
        	//boolean flag=IpHelper.isIpv4(ip);
        	if(ip==null||ip.equals("")||IpHelper.isIpv4(ip)==false){
        		ip=request.getRemoteHost();//自动获取Ip地址
        		System.out.println("ip::"+ip);
        		String city=GeoIPv4.getLocation(InetAddress.getByName(ip)).getCity();
        		yhWeather =	this.getWeathersByArea(city);
        		    
            	/*service=new YahooWeatherService();
            	Channel channel = service.getForecastForLocation(city).first(3).get(0);
            	
            	JSONObject  channelJson = JSONObject.fromObject(channel.toString());
	        	JSONObject  forecastJson = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
	        	JSONObject jsonThree = new JSONObject(); 
	        	jsonThree.putAll(channelJson);
	        	jsonThree.putAll(forecastJson);
            	
            	weather=jsonThree.toString();*/
        		
        	}else{
        	
	        	String city=GeoIPv4.getLocation(InetAddress.getByName(ip)).getCity();
	        	yhWeather = this.getWeathersByArea(city);
	        	/*service=new YahooWeatherService();
	        	Channel channel = service.getForecastForLocation(city).first(3).get(0);
	        	
	        	JSONObject  channelJson = JSONObject.fromObject(channel.toString());
	        	JSONObject  forecastJson = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
	        	JSONObject jsonThree = new JSONObject(); 
	        	jsonThree.putAll(channelJson);
	        	jsonThree.putAll(forecastJson);
	    
	        	
	        	
	        	weather=jsonThree.toString();*/
        	}
        }catch(ErrorMessageException em){
        	System.out.println("city is null");
        	
        	yhWeather=null;
        	
        	
       } catch (Exception e) {
        
          e.printStackTrace();
        }
        return yhWeather;


     }
    
    
    @GET
    @Path("ipaddress/{weatherapiname}/{day}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an Weather by day, ipAddress,weatherApiName", //
            notes = "Gets the Weather specified by the day,scopeId,weatherApiName,ipAddress path parameter", //
            response = String.class)
    public String getWeatherByApi(
           
            @ApiParam(value = "The weatherApiName(sina or yahoo) of the requested Weather", required = true) @PathParam("weatherapiname") String  weatherApiName,
            @ApiParam(value = "The ipAddress of the requested Weather") @QueryParam("ipAddress") String ipAddress,
            @ApiParam(value = "The day of the requested Weather", required = true, defaultValue ="0") @DefaultValue("0") @PathParam("day") Integer day)throws Exception{
        String weatherInfo=null;
        YahooWeatherService service = null;
        
        try {
        	 if(weatherApiName.equals("sina")){
        		 String content= this.getWeatherByIp(ipAddress, day);
        		 
        		 if(content.contains("errorMessage")){
        			 weatherInfo=content;
        		 }else{
        		
        		 WeatherPresentation weatherPresentation=new WeatherPresentation();
        		 org.json.JSONObject myjObject = new org.json.JSONObject(content);
        		 
        		 weatherPresentation.setDate(myjObject.getString("savedate_zhishu"));
     	         weatherPresentation.setLocation(myjObject.getString("city"));
     	         weatherPresentation.setHigh(myjObject.getString("temperature1")); 
     	         weatherPresentation.setLow(myjObject.getString("temperature2"));
     	         weatherPresentation.setText(myjObject.getString("yd_s"));
     	         weatherPresentation.setStatus(myjObject.getString("status1"));
     	         JSONObject jsonRes = JSONObject.fromObject(weatherPresentation);
        	     weatherInfo=jsonRes.toString();
        		 }
        	 }else if(weatherApiName.equals("yahoo")){
        		// weatherInfo=this.getWeathersByIpAddress(ipAddress);
        		 try {
					if(ipAddress==null||ipAddress.equals("")||IpHelper.isIpv4(ipAddress)==false){
						 ipAddress=request.getRemoteHost();//自动获取Ip地址
						System.out.println("ip::"+ipAddress);
						String city=GeoIPv4.getLocation(InetAddress.getByName(ipAddress)).getCity();
						service=new YahooWeatherService();
						Channel channel = service.getForecastForLocation(city).first(3).get(0);
						
						JSONObject  channelJson = JSONObject.fromObject(channel.toString());
						JSONObject  forecastJson = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
						JSONObject jsonThree = new JSONObject(); 
						jsonThree.putAll(channelJson);
						jsonThree.putAll(forecastJson);
						
						weatherInfo=jsonThree.toString();
					 
					 }else{
					    	
						String city=GeoIPv4.getLocation(InetAddress.getByName(ipAddress)).getCity();
						
						service=new YahooWeatherService();
						Channel channel = service.getForecastForLocation(city).first(3).get(0);
						
						JSONObject  channelJson = JSONObject.fromObject(channel.toString());
						JSONObject  forecastJson = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
						JSONObject jsonThree = new JSONObject(); 
						jsonThree.putAll(channelJson);
						jsonThree.putAll(forecastJson);
  	    
						
						
						weatherInfo=jsonThree.toString();
					 }
				} catch (ErrorMessageException em) {
					// TODO Auto-generated catch block
					System.out.println("city is null");
					weatherInfo=em.warnMess();
				}
        	 }else{
        		 weatherInfo=null;
        	 }
         
          
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return weatherInfo;
        
   }
    

    

    
}
