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

package org.eclipse.kapua.service.appversion.internal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.kapua.commons.model.AbstractKapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;



/**
 * AppVersion entity implementation.
 *
 * @since 1.0
 */
@Entity(name = "AppVersion")
@NamedQueries({
    @NamedQuery(name = "AppVersion.queryByPackagename", query = "select ap from AppVersion"
        + " ap where ap.packagename=?1 and ap.types='all' "),
        
    @NamedQuery(name = "AppVersion.queryByDistinct", query = "select ap from AppVersion " 
        + " ap where ap.packagename=?1 and ap.forversion=?2 and  ap.types='.patch' ")
})
@Table(name = "app_version")
public class AppVersionImpl extends AbstractKapuaUpdatableEntity implements AppVersion {

  private static final long serialVersionUID = 8530992430658117928L;
    
 
 
  @Basic
  @Column(name = "packagename", nullable = false)
    private String packagename;
    
    

  @Basic
  @Column(name = "code", nullable = false)
    private String code;
    
  @Basic
  @Column(name = "version", nullable = false)
    private String version;
    
  @Basic
  @Column(name = "url", nullable = false)
    private String url;
    
    
  @Basic
  @Column(name = "size", nullable = false)
    private Integer size;
    
    
  @Basic
  @Column(name = "md5", nullable = false)
    private String md5;
    
    
  @Basic
  @Column(name = "types", nullable = false)
    private String types;
    
    
  @Basic
  @Column(name = "revision", nullable = false)
    private String revision;
    
    
  @Basic
  @Column(name = "forversion", nullable = false)
    private String forversion;
  

    
  /**
   * Constructor.
   */
  protected AppVersionImpl() {
      super();
  }
  
  /**
   * Constructor
   *
   * @param scopeId.
   */
  public AppVersionImpl(KapuaId scopeId) {
      super(scopeId);
    
  }
  
 
  
    
  public String getPackagename() {
    return packagename;
  }

  public void setPackagename(String packagename) {
    this.packagename = packagename;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

  public String getRevision() {
    return revision;
  }

  public void setRevision(String revision) {
    this.revision = revision;
  }

  public String getForversion() {
    return forversion;
  }

  public void setForversion(String forversion) {
    this.forversion = forversion;
  }


}
