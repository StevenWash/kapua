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
package org.eclipse.kapua.service.processrecovery;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.KapuaEntityService;
import org.eclipse.kapua.service.KapuaUpdatableEntityService;
import org.eclipse.kapua.service.config.KapuaConfigurableService;




/**
 * ProcessRecoveryService exposes APIs to manage ProcessRecovery objects.<br>
 * It includes APIs to create, update, find, list and delete ProcessRecovery.<br>
 * Instances of the ProcessRecoveryService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface ProcessRecoveryService extends KapuaEntityService<ProcessRecovery, ProcessRecoveryCreator>,
  KapuaUpdatableEntityService<ProcessRecovery>,
   KapuaConfigurableService{
        



    /**
     * Returns the {@link ProcessRecovery} with elements matching the provided query.
     * 
     * @param query
     *            The {@link ProcessRecoveryQuery} used to filter results. findByDistinct
     * @return The {@link ProcessRecoveryListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
	public ProcessRecovery findById(KapuaId processRecoveryId,KapuaId scopeId)
            throws KapuaException;
	
	
	
	
    public ProcessRecovery create(ProcessRecoveryCreator processRecoveryCreator) throws KapuaException ;
    
    public void delete(KapuaId scopeId, KapuaId entityId)throws KapuaException;
    
    public ProcessRecovery update(ProcessRecovery processRecovery) throws KapuaException ;
}
