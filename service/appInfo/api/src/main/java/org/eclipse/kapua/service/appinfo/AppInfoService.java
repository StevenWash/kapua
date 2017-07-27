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
package org.eclipse.kapua.service.appinfo;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.KapuaEntityService;
import org.eclipse.kapua.service.KapuaUpdatableEntityService;
import org.eclipse.kapua.service.config.KapuaConfigurableService;


/**
 * AppInfoService exposes APIs to manage AppInfo objects.<br>
 * It includes APIs to create, update, find, list and delete Weathers.<br>
 * Instances of the AppInfoService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface AppInfoService extends KapuaEntityService<AppInfo, AppInfoCreator>,
   KapuaUpdatableEntityService<AppInfo>,  KapuaConfigurableService{
  
        



    /**
     * Returns the {@link AppInfo} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results. findByDistinct
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
	public AppInfo findById(KapuaId appInfoId,KapuaId scopeId)
            throws KapuaException;
	
	
	public AppInfo findByPackagename(KapuaId scopeId,String packagename)
            throws KapuaException;
	
	public AppInfo findByDistinct(KapuaId scopeId,String packagename,String forversion)
            throws KapuaException;
	
	

    public AppInfo create(AppInfoCreator appInfoCreator) throws KapuaException ;
    
    public void delete(KapuaId scopeId, KapuaId entityId)throws KapuaException;
    
    public AppInfo update(AppInfo appInfo) throws KapuaException ;
	
  
}