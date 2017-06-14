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

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.KapuaEntityService;
import org.eclipse.kapua.service.KapuaNamedEntityService;
import org.eclipse.kapua.service.KapuaUpdatableEntityService;
import org.eclipse.kapua.service.config.KapuaConfigurableService;

/**
 * AccountService exposes APIs to manage Account objects.<br>
 * It includes APIs to create, update, find, list and delete Accounts.<br>
 * Instances of the AccountService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface WeatherService extends KapuaEntityService<Weather, WeatherCreator>{
        

	
	/**
     * Creates a new {@link Weather} based on the parameters provided in the {@link WeatherCreator}.<br>
     * {@link Weather} must have a unique name.
     * 
     * @param weatherCreator
     *            The creator object from which to create the {@link Weather}.
     * @return The created {@link Weather}
     * @throws KapuaException
     * @since 1.0.0
     */
    public Weather create(WeatherCreator weatherCreator)
            throws KapuaException;
    
    /**
     * Finds the Weather by weather identifiers
     * 
     * @param id
     * @return
     * @throws KapuaException
     */
    public Weather find(KapuaId scopeId, KapuaId weatherId)
            throws KapuaException;
    
    

    /**
     * Returns the {@link WeatherListResult} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results.
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
    public WeatherListResult query(KapuaQuery<Weather> query)
            throws KapuaException;

    /**
     * Returns a List of direct child account of the provided account identifier
     * 
     * @param accountId
     *            the Id of the parent Account
     * @return List of direct child account of an account
     * @throws KapuaException
     */
    
    
    
 
   /* public WeatherListResult findChildsRecursively(KapuaId weatherId)
            throws KapuaException;
    */
    /**
     * Delete the {@link Domain} by scope id and {@link Domain} id.
     * 
     * @param scopeId
     *            The scope id in which to delete.
     * @param roleId
     *            The {@link Domain} id to delete.
     * @throws KapuaException
     * @since 1.0.0
    public void delete(KapuaId scopeId, KapuaId roleId)
            throws KapuaException;
    
    
    /**
     * Returns the count of the {@link Weather} elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results.
     * @return The count of the {@link Weather} elements matching the provided query.
     * @throws KapuaException
     * @since 1.0.0
     */
    public long count(KapuaQuery<Weather> query)
            throws KapuaException;
    
    
    /* * Delete the {@link Weather} by scopeid and {@link Weather} id.
     * 
     * @param scopeId
     *            The scopeid in which to delete.
     * @param roleId
     *            The {@link Weather} id to delete.
     * @throws KapuaException
     * @since 1.0.0*/
    public void delete(KapuaId scopeId, KapuaId roleId)
            throws KapuaException;
    
    
    /**
     * Returns the {@link WeatherListResult} with elements matching the provided query.
     * 
     * @param scopeId
     *            The {@link WeatherQuery} used to filter results.
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
    public WeatherListResult getProvince(KapuaId scopeId)
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
    public WeatherListResult getCityByProvince(KapuaId scopeId,String province)
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
    public WeatherListResult getAreaByCity(KapuaId scopeId,String city)
    		throws KapuaException;
    
    
    
   
    
    /**
	 * 获取天气信息 xinlang
	 * @param area 传入地区
	 * @return 天气的结构信息
	 */
	public String getWeather(String area,int day)throws KapuaException;
}
