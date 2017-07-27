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
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;
import org.eclipse.kapua.service.appversion.AppVersionListResult;


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
    System.out.println("~~~~~~~~~~~~~~~~");
    // Update appVersion
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

  public static void delete(EntityManager em, KapuaId appVersionId) 
      throws KapuaEntityNotFoundException {
    ServiceDAO.delete(em, AppVersionImpl.class, appVersionId);
  }
  
  
  /**
   * Creates and return new AppVersion.
   * 
   */
  public static AppVersion create(EntityManager em, AppVersionCreator accountCreator)
          throws KapuaException {
     
    // Create AppVersion
   
    AppVersion appVersionImpl = new AppVersionImpl(accountCreator.getScopeId());
    appVersionImpl.setPackagename(accountCreator.getPackagename());
    appVersionImpl.setUrl(accountCreator.getUrl());
    appVersionImpl.setSize(accountCreator.getSize());
    appVersionImpl.setVersion(accountCreator.getVersion());
    appVersionImpl.setRevision(accountCreator.getRevision());
    appVersionImpl.setForversion(accountCreator.getForversion());
    appVersionImpl.setMd5(accountCreator.getMd5());
    appVersionImpl.setTypes(accountCreator.getTypes());
    appVersionImpl.setCode(accountCreator.getCode());
    
    return ServiceDAO.create(em, appVersionImpl);
  }
  
  
  public static long count(EntityManager em, KapuaQuery<AppVersion> appVersionQuery)
      throws KapuaException {
    return ServiceDAO.count(em, AppVersion.class,AppVersionImpl.class, appVersionQuery);
  }
  
  
  public static AppVersionListResult query(EntityManager em, KapuaQuery<AppVersion> appVersionQuery)
      throws KapuaException {
    return (AppVersionListResult) ServiceDAO.query(em, AppVersion.class, 
            AppVersionImpl.class, new AppVersionListResultImpl(), appVersionQuery);
  }


}
