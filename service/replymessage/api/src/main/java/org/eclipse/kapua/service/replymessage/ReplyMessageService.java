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
package org.eclipse.kapua.service.replymessage;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.KapuaEntityService;
import org.eclipse.kapua.service.KapuaUpdatableEntityService;
import org.eclipse.kapua.service.config.KapuaConfigurableService;




/**
 * ReplyMessageService exposes APIs to manage ReplyMessage objects.<br>
 * It includes APIs to create, update, find, list and delete ReplyMessage.<br>
 * Instances of the ReplyMessageService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface ReplyMessageService extends KapuaEntityService<ReplyMessage, ReplyMessageCreator>,
  KapuaUpdatableEntityService<ReplyMessage>,
   KapuaConfigurableService{
        



    /**
     * Returns the {@link ReplyMessage} with elements matching the provided query.
     * 
     * @param query
     *            The {@link WeatherQuery} used to filter results. findByDistinct
     * @return The {@link WeatherListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
	public ReplyMessage findById(KapuaId replyMessageId,KapuaId scopeId)
            throws KapuaException;
	
	
	
	
    public ReplyMessage create(ReplyMessageCreator replyMessageCreator) throws KapuaException ;
    
    public void delete(KapuaId scopeId, KapuaId entityId)throws KapuaException;
    
    public ReplyMessage update(ReplyMessage appVersion) throws KapuaException ;
}
