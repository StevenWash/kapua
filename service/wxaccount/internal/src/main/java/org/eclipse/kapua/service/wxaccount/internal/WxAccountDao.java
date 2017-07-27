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

package org.eclipse.kapua.service.wxaccount.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.wxaccount.WxAccount;
import org.eclipse.kapua.service.wxaccount.WxAccountCreator;
import org.eclipse.kapua.service.wxaccount.WxAccountListResult;



/**
 * AppVersion DAO
 * 
 * @since 1.0
 *
 */
public class WxAccountDao {

  private WxAccountDao() {
  }



  /**
   * update the AppVersion by appVersion
   * 
   * @param em not null
   * @param appVersion  not null
   * @return  not null.
   */
  public static WxAccount update(EntityManager em, WxAccount wxAccount)
            throws KapuaException {
    //
    System.out.println("~~~~~~~~~~~~~~~~");
    // Update appVersion
    WxAccountImpl wxAccountImpl = (WxAccountImpl) wxAccount;

    return ServiceDAO.update(em, WxAccountImpl.class, wxAccountImpl);
  }

  /**
   * find the AppVersion by appVersionId
   * 
   * @param appVersionId not null
  
   * @return  not null.
   */
  public static WxAccount find(EntityManager em, KapuaId wxAccountId) {
	  WxAccount wxAccount = null;
    try {
    	wxAccount = em.find(WxAccountImpl.class, wxAccountId);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return  wxAccount;
  }
     

  public static void delete(EntityManager em, KapuaId accountId) 
	      throws KapuaEntityNotFoundException {
	    ServiceDAO.delete(em, WxAccountImpl.class, accountId);
	  }
  
  
  /**
   * Creates and return new AppVersion.
   * 
   */
  public static WxAccount create(EntityManager em, WxAccountCreator wxAccountCreator)
          throws KapuaException {
     
    // Create AppVersion
   
	  WxAccount wxAccountImpl = new WxAccountImpl(wxAccountCreator.getScopeId());
	  
	  wxAccountImpl.setUrlName(wxAccountCreator.getUrlName());
	  wxAccountImpl.setWxAppid(wxAccountCreator.getWxAppid());
	  wxAccountImpl.setWxAppsecret(wxAccountCreator.getWxAppsecret());
	  wxAccountImpl.setWxAeskey(wxAccountCreator.getWxAeskey());
	  wxAccountImpl.setWxToken(wxAccountCreator.getWxToken());
	  wxAccountImpl.setName(wxAccountCreator.getName());
    
    return ServiceDAO.create(em, wxAccountImpl);
  }
  
  
  public static long count(EntityManager em, KapuaQuery<WxAccount> appVersionQuery)
      throws KapuaException {
    return ServiceDAO.count(em, WxAccount.class,WxAccountImpl.class, appVersionQuery);
  }
  
  
  public static WxAccountListResult query(EntityManager em, KapuaQuery<WxAccount> appVersionQuery)
      throws KapuaException {
    return (WxAccountListResult) ServiceDAO.query(em, WxAccount.class, WxAccountImpl.class, new WxAccountListResultImpl(), appVersionQuery);
  }
  
  
  /**
   * Finds the WxAccount by name
   * 
   * @param em
   * @param name
   * @return
   */
  public static WxAccount findByName(EntityManager em, String name) {
      return ServiceDAO.findByField(em, WxAccountImpl.class, "name", name);
  }


}
