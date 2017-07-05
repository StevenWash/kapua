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

package org.eclipse.kapua.service.appversion.internal;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;


/**
 * AppVersion DAO
 * 
 * @since 1.0
 *
 */
public class AppVersionDao {

  private AppVersionDao() {
  }



  /**
   * update the AppVersion by appVersion
   * 
   * @param em not null
   * @param appVersion  not null
   * @return  not null.
   */
  public static AppVersion update(EntityManager em, AppVersion appVersion)
            throws KapuaException {
    //
    // Update account
    AppVersionImpl appVersionImpl = (AppVersionImpl) appVersion;

    return ServiceDAO.update(em, AppVersionImpl.class, appVersionImpl);
  }

  /**
   * find the AppVersion by appVersionId
   * 
   * @param appVersionId not null
  
   * @return  not null.
   */
  public static AppVersion find(EntityManager em, KapuaId appVersionId) {
    AppVersion appVersion = null;
    try {
      appVersion = em.find(AppVersionImpl.class, appVersionId);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return  appVersion;
  }
     

  /**
     * Finds the AppVersion by name
     * 
     * @param em not null
     * @param name  not null
     * @return  not null.
     */
  public static AppVersion findByName(EntityManager em, String name) {
    return ServiceDAO.findByField(em, AppVersionImpl.class, "name", name);
  }



}
