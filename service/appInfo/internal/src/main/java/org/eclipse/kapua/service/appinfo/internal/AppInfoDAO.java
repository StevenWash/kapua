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
package org.eclipse.kapua.service.appinfo.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoCreator;

/**
 * AppInfo DAO
 * 
 * @since 1.0
 *
 */
public class AppInfoDAO {

    private AppInfoDAO() {
    }



    /**
     * Updates the provided AppInfo
     * 
     * @param em
     * @param appInfo
     * @return
     * @throws KapuaException
     */
    public static AppInfo update(EntityManager em, AppInfo appInfo)
            throws KapuaException {
        //
        // Update appInfo
    	AppInfoImpl appInfoImpl = (AppInfoImpl) appInfo;

        return ServiceDAO.update(em, AppInfoImpl.class, appInfoImpl);
    }

    /**
     * Deletes the appInfo by account identifier
     * 
     * @param em
     * @param appInfoId
     * @throws KapuaEntityNotFoundException
     *             If the {@link AppInfo} is not found
 
    /**
     * Finds the appInfo by account identifier
     */
    public static AppInfo find(EntityManager em, KapuaId appInfoId) {
    	AppInfo appInfo=null;
      try {
    	  appInfo=em.find(AppInfoImpl.class, appInfoId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return  appInfo;
    }
     

    /**
     * Finds the appInfo by name
     * 
     * @param em
     * @param name
     * @return
     */
    public static AppInfo findByName(EntityManager em, String name) {
        return ServiceDAO.findByField(em, AppInfoImpl.class, "name", name);
    }


    /**
     * Creates and return new AppInfo.
     * 
     */
    public static AppInfo create(EntityManager em, AppInfoCreator appInfoCreator)
            throws KapuaException {
       
      // Create AppVersion
     
    	AppInfo appInfoImpl = new AppInfoImpl(appInfoCreator.getScopeId());
    	
    	appInfoImpl.setComment(appInfoCreator.getComment());
    	appInfoImpl.setImage(appInfoCreator.getImage());
    	appInfoImpl.setPackagename(appInfoCreator.getPackagename());
    	appInfoImpl.setName(appInfoCreator.getName());
    	appInfoImpl.setTypes(appInfoCreator.getTypes());
    	
      return ServiceDAO.create(em, appInfoImpl);
    }
    
    public static void delete(EntityManager em, KapuaId appInfoId) 
    	      throws KapuaEntityNotFoundException {
    	    ServiceDAO.delete(em, AppInfoImpl.class, appInfoId);
    	  }

}
