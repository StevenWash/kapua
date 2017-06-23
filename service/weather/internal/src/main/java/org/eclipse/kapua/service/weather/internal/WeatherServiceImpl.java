package org.eclipse.kapua.service.weather.internal;


import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherListResult;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.authorization.domain.Domain;

import javax.persistence.Query;

import org.eclipse.kapua.service.weather.BaseWeatherInfo;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.eclipse.kapua.service.weather.util.HttpUtil;
import org.eclipse.kapua.service.weather.util.XmlUtil;
import org.eclipse.kapua.service.weather.util.CharTools;




/**
 * {@link weatherService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class WeatherServiceImpl extends AbstractKapuaService implements WeatherService {

    private static final Domain WEATHER_DOMAIN = new WeatherDomain();

    public WeatherServiceImpl() {
        super(WeatherEntityManagerFactory.getInstance());
    }

  
 
     public List<String> getProvince()throws KapuaException{
    	 
         
    	   
    	   // Check Access
           KapuaLocator locator = KapuaLocator.getInstance();
           AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
           PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
           authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
    	
    	   return entityManagerSession.onResult(em -> {
    		  
    		   List<String> provinceLists=new ArrayList<String>();
        	Query  q;
            try {
				q = em.createNamedQuery("Weather.queryProvince",Weather.class);
				
				provinceLists=q.getResultList();
	           
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
             return provinceLists;
        });
    }
     
     
     public List<String> getCityByProvince(String province)throws KapuaException{
  	 
  	   ArgumentValidator.notNull(province, "province");
       
  	 
  	   // Check Access
         KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
         authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
  	
  	   return entityManagerSession.onResult(em -> {
  		 List<String> cityList=new ArrayList<String>();
  		 
      	  Query  q;
          q = em.createNamedQuery("Weather.queryCityByProvince",Weather.class);
          q.setParameter(1,province);
         
          cityList=q.getResultList();
         
          return cityList;
      });
  }
     
     
     
     
     public List<String> getAreaByCity(String city)throws KapuaException{
    	   
    	   ArgumentValidator.notNull(city, "city");
         
    	  
    	   // Check Access
           KapuaLocator locator = KapuaLocator.getInstance();
           AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
           PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
           authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
    	
    	   return entityManagerSession.onResult(em -> {
    		   List<String> areaList=new ArrayList<String>();
        
        	Query  q;
            q = em.createNamedQuery("Weather.queryAreaByCity",Weather.class);
            q.setParameter(1,city);
            
         
            areaList=q.getResultList();
            
            return areaList;
        });
    }
     
     
     
     
     
     public String getWeather(String area,int day)throws KapuaException{
    	 ArgumentValidator.notNull(area, "area");
  	     ArgumentValidator.notNull(day, "day");
  	     
  	   // Check Access
         KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
         authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
    	 
    	 String password = "DJOYnieT8234jlsK";
    	 
    	 Map<String, String> map;
    	 
    	 String result = null;
    	 try {
 			String url = "http://php.weather.sina.com.cn/xml.php?city=" + CharTools.GB2312URLencode(area)
 					+ "&password=" + password + "&day=" + day;
 			
 			String httpResult = HttpUtil.getHttpConnHtml(url);
 			
 			
 			XmlUtil util = new XmlUtil(httpResult);
 			map = util.getContents();
 			
 			BaseWeatherInfo weatherInfo = new SinaWeatherInfo();
 		
 			weatherInfo.doParser(map);
 			
 			result = weatherInfo.buildJsonMsg();
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	 return result;
     }









}
