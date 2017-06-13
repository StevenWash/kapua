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
package org.eclipse.kapua.service.weather.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.model.query.predicate.AttributePredicate;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherCreator;
import org.eclipse.kapua.service.weather.WeatherFactory;
import org.eclipse.kapua.service.weather.WeatherListResult;
import org.eclipse.kapua.service.weather.WeatherQuery;
import org.eclipse.kapua.service.weather.WeatherService;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;

import org.eclipse.kapua.service.authorization.domain.Domain;

import javax.persistence.Query;
import org.eclipse.kapua.service.weather.BaseWeatherInfo;
import java.util.Map;
import org.eclipse.kapua.service.weather.util.HttpUtil;
import org.eclipse.kapua.service.weather.util.XmlUtil;
import org.eclipse.kapua.service.weather.util.CharTools;
import org.eclipse.kapua.service.weather.BaseIpInfo;



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

    @Override
    public Weather create(WeatherCreator weatherCreator)
            throws KapuaException {
        ArgumentValidator.notNull(weatherCreator, "weatherCreator");
        ArgumentValidator.notEmptyOrNull(weatherCreator.getProvince(), "weatherCreator.province");
        ArgumentValidator.notEmptyOrNull(weatherCreator.getCity(), "weatherCreator.city");
        ArgumentValidator.notEmptyOrNull(weatherCreator.getArea(), "weatherCreator.area");
        ArgumentValidator.notEmptyOrNull(weatherCreator.getCity_code(), "weatherCreator.city_code");
        

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.write, null));

        return entityManagerSession.onTransactedInsert(em -> WeatherDAO.create(em, weatherCreator));
    }

    @Override
    public void delete(KapuaId scopeId, KapuaId weatherId) throws KapuaException {
        ArgumentValidator.notNull(weatherId, "weatherId");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.delete, null));

        entityManagerSession.onTransactedAction(em -> {
            if (WeatherDAO.find(em, weatherId) == null) {
                throw new KapuaEntityNotFoundException(Weather.TYPE, weatherId);
            }

            WeatherDAO.delete(em, weatherId);
        });
    }

    @Override
    public Weather find(KapuaId scopeId, KapuaId weatherId)
            throws KapuaException {
        ArgumentValidator.notNull(weatherId, "weatherId");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));

        return entityManagerSession.onResult(em -> WeatherDAO.find(em, weatherId));
    }

/*    @Override
    public weather findByServiceName(String serviceName)
            throws KapuaException {
        ArgumentValidator.notEmptyOrNull(serviceName, "serviceName");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(weather_weather, Actions.read, KapuaId.ANY));

        return entityManagerSession.onResult(em -> {
            weatherFactory weatherFactory = locator.getFactory(weatherFactory.class);
            weatherQuery query = weatherFactory.newQuery(null);
            query.setPredicate(new AttributePredicate<String>(weatherPredicates.SERVICE_NAME, serviceName));

            weatherListResult results = weatherDAO.query(em, query);

            weather weather = null;
            if (!results.isEmpty()) {
                weather = results.getItem(0);
            }
            return weather;
        });
    }*/

    @Override
    public WeatherListResult query(KapuaQuery<Weather> query)
            throws KapuaException {
        ArgumentValidator.notNull(query, "query");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));

        return entityManagerSession.onResult(em -> WeatherDAO.query(em, query));
    }

    @Override
    public long count(KapuaQuery<Weather> query)
            throws KapuaException {
        ArgumentValidator.notNull(query, "query");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));

        return entityManagerSession.onResult(em -> WeatherDAO.count(em, query));
    }
    
    
     public WeatherListResult getProvince(KapuaId scopeId)throws KapuaException{
    	   ArgumentValidator.notNull(scopeId, "scopeId");
         
    	   
    	   // Check Access
           KapuaLocator locator = KapuaLocator.getInstance();
           AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
           PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
           authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
    	
    	   return entityManagerSession.onResult(em -> {
    		   WeatherListResult result = null;
        
        	Query  q;
            q = em.createNamedQuery("Weather.queryProvince",Weather.class);
            result = new WeatherListResultImpl();
            result.addItems(q.getResultList());
            
           
            return result;
        });
    }
     
     
     public WeatherListResult getCityByProvince(KapuaId scopeId,String province)throws KapuaException{
  	   ArgumentValidator.notNull(scopeId, "scopeId");
  	   ArgumentValidator.notNull(province, "province");
       
  	   
  	   // Check Access
         KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
         authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
  	
  	   return entityManagerSession.onResult(em -> {
  		   WeatherListResult result = null;
      
      	  Query  q;
          q = em.createNamedQuery("Weather.queryCityByProvince",Weather.class);
          q.setParameter(1,province);
          result = new WeatherListResultImpl();
          result.addItems(q.getResultList());
          
          return result;
      });
  }
     
     
     
     
     public WeatherListResult getAreaByCity(KapuaId scopeId,String city)throws KapuaException{
    	   ArgumentValidator.notNull(scopeId, "scopeId");
    	   ArgumentValidator.notNull(city, "city");
         
    	   
    	   // Check Access
           KapuaLocator locator = KapuaLocator.getInstance();
           AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
           PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
           authorizationService.checkPermission(permissionFactory.newPermission(WEATHER_DOMAIN, Actions.read, KapuaId.ANY));
    	
    	   return entityManagerSession.onResult(em -> {
    		   WeatherListResult result = null;
        
        	Query  q;
            q = em.createNamedQuery("Weather.queryAreaByCity",Weather.class);
            q.setParameter(1,city);
            result = new WeatherListResultImpl();
            result.addItems(q.getResultList());
            
            return result;
        });
    }
     
     
     public String getInformation(String ip)throws KapuaException{
    	 String result = "";
    	 try {
 			String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip;
 			
 			System.out.println(url);
 			String httpResult = HttpUtil.getHttpConnHtml(url);
 			System.out.println(httpResult);
 			
 			BaseIpInfo ipInfo = new SinaIpInfo();
 			
 			ipInfo.doParser(httpResult);
 			
 			result = ipInfo.buildJsonMsg();
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	  return result;
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
 			System.out.println(url);
 			String httpResult = HttpUtil.getHttpConnHtml(url);
 			System.out.println(httpResult);
 			
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
