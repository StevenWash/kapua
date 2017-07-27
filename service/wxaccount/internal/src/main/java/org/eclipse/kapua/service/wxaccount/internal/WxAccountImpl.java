/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.kapua.commons.model.AbstractKapuaNamedEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.wxaccount.WxAccount;



/**
 * WxAccount entity implementation.
 *
 * @since 1.0
 */

@Entity(name = "WxAccount")
@Table(name = "wx_account")
public class WxAccountImpl extends AbstractKapuaNamedEntity implements WxAccount {
    

  /**
	 * 
	 */
	private static final long serialVersionUID = 1478246296277402880L;



  @Basic
  @Column(name = "wx_appid", nullable = false)
    private String wxAppid;
    
    

  @Basic
  @Column(name = "wx_appsecret", nullable = false)
    private String wxAppsecret;
    
  @Basic
  @Column(name = "wx_token", nullable = false)
    private String wxToken;
    
  @Basic
  @Column(name = "wx_aeskey", nullable = false)
    private String wxAeskey;
    
    
  @Basic
  @Column(name = "url_name", nullable = false)
    private String urlName;
    

  

    
  /**
   * Constructor.
   */
  protected WxAccountImpl() {
      super();
  }
  
  /**
   * Constructor
   *
   * @param scopeId.
   */
  public WxAccountImpl(KapuaId scopeId) {
      super(scopeId);
    
  }

/**
 * @return the wxAppid
 */
public String getWxAppid() {
	return wxAppid;
}

/**
 * @param wxAppid the wxAppid to set
 */
public void setWxAppid(String wxAppid) {
	this.wxAppid = wxAppid;
}

/**
 * @return the wxAppsecret
 */
public String getWxAppsecret() {
	return wxAppsecret;
}

/**
 * @param wxAppsecret the wxAppsecret to set
 */
public void setWxAppsecret(String wxAppsecret) {
	this.wxAppsecret = wxAppsecret;
}

/**
 * @return the wxToken
 */
public String getWxToken() {
	return wxToken;
}

/**
 * @param wxToken the wxToken to set
 */
public void setWxToken(String wxToken) {
	this.wxToken = wxToken;
}

/**
 * @return the wxAeskey
 */
public String getWxAeskey() {
	return wxAeskey;
}

/**
 * @param wxAeskey the wxAeskey to set
 */
public void setWxAeskey(String wxAeskey) {
	this.wxAeskey = wxAeskey;
}

/**
 * @return the urlName
 */
public String getUrlName() {
	return urlName;
}

/**
 * @param urlName the urlName to set
 */
public void setUrlName(String urlName) {
	this.urlName = urlName;
}








  
 
  
    



}
