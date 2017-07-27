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
package org.eclipse.kapua.service.apkinfo;



import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;

import org.eclipse.kapua.service.KapuaService;


/**
 * WeatherService exposes APIs to manage Weather objects.<br>
 * It includes APIs to create, update, find, list and delete Weathers.<br>
 * Instances of the WeatherService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface ApkInfoService extends KapuaService{
        



    /**
     * Returns the {@link ApkInfo} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results. findByDistinct
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
	public ApkInfo findById(KapuaId apkInfoId,KapuaId scopeId)
            throws KapuaException;
	
	
	public ApkInfo findByPackagename(KapuaId scopeId,String packagename)
            throws KapuaException;
	
	public ApkInfo findByDistinct(KapuaId scopeId,String packagename,String forversion)
            throws KapuaException;
	
  
}