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
package org.eclipse.kapua.service.appversion;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.KapuaService;



/**
 * AppVersionService exposes APIs to manage AppVersion objects.<br>
 * It includes APIs to create, update, find, list and delete AppVersion.<br>
 * Instances of the AppVersionService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface AppVersionService extends KapuaService{
        



    /**
     * Returns the {@link AppVersion} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results. findByDistinct
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
	public AppVersion findById(KapuaId appVersionId,KapuaId scopeId)
            throws KapuaException;
	
	
	public AppVersion findByPackagename(KapuaId scopeId,String packagename)
            throws KapuaException;
	
	public AppVersion findByDistinct(KapuaId scopeId,String packagename,String forversion)
            throws KapuaException;
	
    public AppVersion create(AppVersionCreator appVersionCreator) throws KapuaException ;
    
    public void delete(KapuaId scopeId, KapuaId entityId)throws KapuaException;
}
