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

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;


/**
 * AppVersion DAO
 * 
 * @since 1.0
 *
 */
public class AppVersionDAO {

  private AppVersionDAO() {
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

  public static void delete(EntityManager em, KapuaId accountId) 
      throws KapuaEntityNotFoundException {
    ServiceDAO.delete(em, AppVersionImpl.class, accountId);
  }
  
  
  /**
   * Creates and return new AppVersion.
   * 
   */
  public static AppVersion create(EntityManager em, AppVersionCreator accountCreator)
          throws KapuaException {
     
    // Create AppVersion
    System.out.println("appversionDao -----create");
    AppVersionImpl appVersionImpl = new AppVersionImpl();
    appVersionImpl.setPackagename(accountCreator.getPackagename());
    appVersionImpl.setUrl(accountCreator.getUrl());
    appVersionImpl.setSize(accountCreator.getSize());
    appVersionImpl.setVersion(accountCreator.getVersion());
    appVersionImpl.setRevision(accountCreator.getRevision());
    appVersionImpl.setForversion(accountCreator.getForversion());
    appVersionImpl.setMd5(accountCreator.getMd5());
    appVersionImpl.setTypes(accountCreator.getTypes());
    appVersionImpl.setCode(accountCreator.getCode());
    System.out.println("appversionDao -----create2");
    return ServiceDAO.create(em, appVersionImpl);
  }


}
