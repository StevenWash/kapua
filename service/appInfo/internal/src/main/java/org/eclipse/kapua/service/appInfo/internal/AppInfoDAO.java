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
package org.eclipse.kapua.service.appInfo.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;

import org.eclipse.kapua.service.appInfo.AppInfo;

/**
 * Account DAO
 * 
 * @since 1.0
 *
 */
public class AppInfoDAO {

    private AppInfoDAO() {
    }



    /**
     * Updates the provided account
     * 
     * @param em
     * @param account
     * @return
     * @throws KapuaException
     */
    public static AppInfo update(EntityManager em, AppInfo appInfo)
            throws KapuaException {
        //
        // Update account
    	AppInfoImpl appInfoImpl = (AppInfoImpl) appInfo;

        return ServiceDAO.update(em, AppInfoImpl.class, appInfoImpl);
    }

    /**
     * Deletes the account by account identifier
     * 
     * @param em
     * @param accountId
     * @throws KapuaEntityNotFoundException
     *             If the {@link Account} is not found
 
    /**
     * Finds the account by account identifier
     */
    public static AppInfo find(EntityManager em, KapuaId appInfoId) {
    	AppInfo appInfo=null;
    	System.out.println("appInfoId:::"+appInfoId);
      try {
    	  appInfo=em.find(AppInfoImpl.class, appInfoId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return  appInfo;
    }
     

    /**
     * Finds the account by name
     * 
     * @param em
     * @param name
     * @return
     */
    public static AppInfo findByName(EntityManager em, String name) {
        return ServiceDAO.findByField(em, AppInfoImpl.class, "name", name);
    }



}
