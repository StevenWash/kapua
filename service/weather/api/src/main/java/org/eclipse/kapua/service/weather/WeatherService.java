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
package org.eclipse.kapua.service.weather;

import java.util.List;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.KapuaEntityService;
import org.eclipse.kapua.service.KapuaNamedEntityService;
import org.eclipse.kapua.service.KapuaService;
import org.eclipse.kapua.service.KapuaUpdatableEntityService;
import org.eclipse.kapua.service.config.KapuaConfigurableService;

/**
 * WeatherService exposes APIs to manage Weather objects.<br>
 * It includes APIs to create, update, find, list and delete Weathers.<br>
 * Instances of the WeatherService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface WeatherService extends KapuaService{
        

	
	
    
 /*   *//**
     * Finds the Weather by weather identifiers
     * 
     * @param id
     * @return
     * @throws KapuaException
     *//*
    public Weather find(KapuaId scopeId, KapuaId weatherId)
            throws KapuaException;*/
    
    

    /**
     * Returns the {@link WeatherListResult} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results.
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
   /* public WeatherListResult query(KapuaQuery<Weather> query)
            throws KapuaException;
*/
   
    

  
    
  
    
    
    /**
     * Returns the {@link WeatherListResult} with elements matching the provided query.
     * 
     * @param scopeId
     *            The {@link WeatherQuery} used to filter results.
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
    public List<String> getProvince(KapuaId scopeId)
    		throws KapuaException;
    
    
    
    /**
     * Returns the {@link WeatherListResult} with elements matching the provided query.
     * 
     * @param scopeId
     *            The {@link WeatherQuery} used to filter results.
     * @param province
     *            The {@link WeatherQuery} used to filter results.           
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
    public List<String> getCityByProvince(KapuaId scopeId,String province)
    		throws KapuaException;
    
    
    
    
    /**
     * Returns the {@link WeatherListResult} with elements matching the provided query.
     * 
     * @param scopeId
     *            The {@link WeatherQuery} used to filter results.
     * @param city
     *            The {@link WeatherQuery} used to filter results.           
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
    public List<String> getAreaByCity(KapuaId scopeId,String city)
    		throws KapuaException;
    
    
    
   
    
    /**
	 * 获取天气信息 xinlang
	 * @param area 传入地区
	 * @return 天气的结构信息
	 */
	public String getWeather(String area,int day)throws KapuaException;
}
