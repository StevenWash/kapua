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
package org.eclipse.kapua.service.apkinfo.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.apkinfo.ApkInfo;

/**
 * Account DAO
 * 
 * @since 1.0
 *
 */
public class ApkInfoDAO {

    private ApkInfoDAO() {
    }



    /**
     * Updates the provided account
     * 
     * @param em
     * @param account
     * @return
     * @throws KapuaException
     */
    public static ApkInfo update(EntityManager em, ApkInfo apkInfo)
            throws KapuaException {
        //
        // Update account
    	ApkInfoImpl apkInfoImpl = (ApkInfoImpl) apkInfo;

        return ServiceDAO.update(em, ApkInfoImpl.class, apkInfoImpl);
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
    public static ApkInfo find(EntityManager em, KapuaId apkInfoId) {
    	ApkInfo apkInfo=null;
      try {
		apkInfo=em.find(ApkInfoImpl.class, apkInfoId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return  apkInfo;
    }
     

    /**
     * Finds the account by name
     * 
     * @param em
     * @param name
     * @return
     */
    public static ApkInfo findByName(EntityManager em, String name) {
        return ServiceDAO.findByField(em, ApkInfoImpl.class, "name", name);
    }



}
