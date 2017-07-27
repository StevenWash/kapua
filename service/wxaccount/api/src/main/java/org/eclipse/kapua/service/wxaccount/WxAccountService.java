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
package org.eclipse.kapua.service.wxaccount;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.KapuaEntityService;
import org.eclipse.kapua.service.KapuaService;
import org.eclipse.kapua.service.KapuaUpdatableEntityService;
import org.eclipse.kapua.service.config.KapuaConfigurableService;




/**
 * AppVersionService exposes APIs to manage AppVersion objects.<br>
 * It includes APIs to create, update, find, list and delete AppVersion.<br>
 * Instances of the AppVersionService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface WxAccountService extends KapuaEntityService<WxAccount, WxAccountCreator>,
  KapuaUpdatableEntityService<WxAccount>,
   KapuaConfigurableService{
        



    /**
     * Returns the {@link WxAccount} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results. findByDistinct
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
	public WxAccount findById(KapuaId appVersionId,KapuaId scopeId)
            throws KapuaException;
	
	

	
    public WxAccount create(WxAccountCreator appVersionCreator) throws KapuaException ;
    
    public void delete(KapuaId scopeId, KapuaId entityId)throws KapuaException;
    
    public WxAccount update(WxAccount appVersion) throws KapuaException ;
    
    public String getTokenByName(String name) throws KapuaException ;
}
