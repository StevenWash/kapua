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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.app.common.util.IpHelper;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.weather.internal.GeoIPv4;
import org.eclipse.kapua.service.weather.internal.NormalResult;
import org.eclipse.kapua.service.weather.BaseIpService;
import org.eclipse.kapua.service.weather.internal.Forecast;
import org.eclipse.kapua.service.weather.internal.SinaIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;
import org.eclipse.kapua.service.weather.internal.WeatherPresentation;
import org.eclipse.kapua.service.weather.internal.YahooWeatherService;
import org.eclipse.kapua.service.weather.internal.Channel;
import org.eclipse.kapua.service.weather.util.ErrorMessageException;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
    @Path("sina/getWeather/{city}/{day}")    
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
        	boolean flag=IpHelper.isIpv4(ip); 
        	if(ip==null||ip.equals("")||flag==false){//自动获取Ip
        		    ip = request.getHeader("x-forwarded-for");  
        	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        	            ip = request.getHeader("Proxy-Client-IP");  
        	        }  
        	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        	            ip = request.getHeader("WL-Proxy-Client-IP");  
        	        }  
        	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        	            ip = request.getHeader("HTTP_CLIENT_IP");  
        	        }  
        	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        	        }  
        	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        	            ip = request.getRemoteAddr();  
        	        } 
        	       
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
			
				//if(city==null||city.equals("")){
					/*ResultList res=new ResultList();
					res.setErrorMessage("city is null");
					net.sf.json.JSONObject jsonRes = net.sf.json.JSONObject.fromObject(res);
					strResult=jsonRes.toString();*/
				//	throw new MessageException();
				
			//	}else{
				    String content = weatherService.getWeather(city, day);
						if(content!=null&&!content.equals("")){
							result.setResult(content);
				//		}
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
    @Path("YaHooApi/{city}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an Weather by YaHooWeatherApi and area", //
            notes = "Gets the Weather specified by the area path parameter", //
            response = String.class)
    public String getWeathersByArea(
          
            @ApiParam(value = "The area of the requested Weather", required = true) @PathParam("city") String city)
            {
    	StringBuffer buffer =new StringBuffer();
    	YahooWeatherService service = null;
        String  weathers = null;
        try {
        	service=new YahooWeatherService();
        	Channel channel = service.getForecastForLocation(city).first(3).get(0);
        	//buffer.append(channel);
        /*	List<Forecast> list=channel.getItem().getForecasts();
    		for(Forecast fo:list){
    			builder.append("day: "+fo.getDay()+"  date:"+fo.getDate()+"  high:"+fo.getHigh()+" low:"+fo.getLow()+"  code:"+fo.getCode()+"     text:"+fo.getText()+"\n");
    		}*/
        //	buffer.append(channel.getItem().getForecasts().get(0));
        	JSONObject  myJson = JSONObject.fromObject(channel.toString());
        	JSONObject  myJson1 = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
        	JSONObject jsonThree = new JSONObject(); 
        	jsonThree.putAll(myJson);
        	jsonThree.putAll(myJson1);
        	weathers=jsonThree.toString();
        	
          
        } catch (Exception e) {
           e.printStackTrace();
        }
        return weathers;


     }
    
    
    @GET
    @Path("YaHooApi/ip/weather")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an Weather by YaHooWeatherApi and ipAddress,scopeId", //
            notes = "Gets the Weather specified by the ipAddress path parameter", //
            response = String.class)
    public String getWeathersByIpAddress(
           
            @ApiParam(value = "The ipAddress of the requested Weather") @QueryParam("ipAddress") String ip){
            
    	StringBuffer buffer =new StringBuffer(); 
    	YahooWeatherService service = null;
        String weather=null;
        try {
        	boolean flag=IpHelper.isIpv4(ip);
        	if(ip==null||ip.equals("")||flag==false){
        		ip=request.getRemoteHost();//自动获取Ip地址
        		String city=GeoIPv4.getLocation(InetAddress.getByName(ip)).getCity();
        		
            	service=new YahooWeatherService();
            	Channel channel = service.getForecastForLocation(city).first(3).get(0);
            	/*buffer.append(channel);
            	buffer.append(channel.getItem().getForecasts().get(0));*/
            	JSONObject  myJson = JSONObject.fromObject(channel.toString());
	        	JSONObject  myJson1 = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
	        	JSONObject jsonThree = new JSONObject(); 
	        	jsonThree.putAll(myJson);
	        	jsonThree.putAll(myJson1);
            	
            	weather=jsonThree.toString();
        	}else{
        	
	        	String city=GeoIPv4.getLocation(InetAddress.getByName(ip)).getCity();
	        	service=new YahooWeatherService();
	        	Channel channel = service.getForecastForLocation(city).first(3).get(0);
	        	
	        	JSONObject  myJson = JSONObject.fromObject(channel.toString());
	        	JSONObject  myJson1 = JSONObject.fromObject(channel.getItem().getForecasts().get(0).toString());
	        	JSONObject jsonThree = new JSONObject(); 
	        	jsonThree.putAll(myJson);
	        	jsonThree.putAll(myJson1);
	        //	buffer.append(channel.getItem().getForecasts().get(0));
	        //	System.out.println("buffer.toString:"+buffer.toString());
	        	
	        	/*net.sf.json.JSONObject  myJson = net.sf.json.JSONObject.fromObject(buffer.toString());
	        	System.out.println("myJson:"+myJson.toString());*/
	        	/*System.out.println("buffer::"+buffer.toString());
	        	org.json.JSONArray jsonArray = new org.json.JSONArray(buffer.toString());
	        	System.out.println("jsonArray::"+jsonArray.toString());
	        	System.out.println();*/
	        //	buffer.append(channel.getItem().getForecasts().get(0));
	        	/*List<Forecast> list=channel.getItem().getForecasts();
	    		for(Forecast fo:list){
	    			builder.append("day: "+fo.getDay()+"  date:"+fo.getDate()+"  high:"+fo.getHigh()+" low:"+fo.getLow()+"  code:"+fo.getCode()+"     text:"+fo.getText()+"\n");
	    		}*/
	        	/*Forecast forecase=channel.getItem().getForecasts().get(0);
	        	net.sf.json.JSONObject jsonForecase = net.sf.json.JSONObject.fromObject(forecase);
	        	buffer.append(jsonForecase.toString());*/
	        	
	        	
	        	weather=jsonThree.toString();
        	}
        }catch(ErrorMessageException em){
        	System.out.println("city is null");
        	weather=em.warnMess();
        	
       } catch (Exception e) {
        
      
        	   /* ResultList res=new ResultList();
				res.setErrorMessage("city is null");
				net.sf.json.JSONObject jsonRes = net.sf.json.JSONObject.fromObject(res);
				weather=jsonRes.toString();*/
				
			
            e.printStackTrace();
        }
        return weather;


     }
    
    
    @GET
    @Path("weathers/{weatherApiName}/{ipAddress}/{day}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an Weather by day, ipAddress,weatherApiName", //
            notes = "Gets the Weather specified by the day,scopeId,weatherApiName,ipAddress path parameter", //
            response = String.class)
    public String getWeatherByApi(
           
            @ApiParam(value = "The weatherApiName of the requested Weather", required = true) @PathParam("weatherApiName") String  weatherApiName,
            @ApiParam(value = "The ipAddress of the requested Weather", required = true) @PathParam("ipAddress") String ipAddress,
            @ApiParam(value = "The day of the requested Weather", required = true, defaultValue ="0") @DefaultValue("0") @PathParam("day") Integer day)throws Exception{
        String weatherInfo=null;
        
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
        		 weatherInfo=this.getWeathersByIpAddress(ipAddress);
        	 }else{
        		 weatherInfo=null;
        	 }
         
          
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return weatherInfo;
        
   }
    

    

    
}
