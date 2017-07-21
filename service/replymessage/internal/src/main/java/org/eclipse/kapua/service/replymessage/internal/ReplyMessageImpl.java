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

package org.eclipse.kapua.service.replymessage.internal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.kapua.commons.model.AbstractKapuaUpdatableEntity;
import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.replymessage.ReplyMessage;



/**
 * ReplyMessage entity implementation.
 *
 * @since 1.0
 */

@Entity(name = "ReplyMessage")
@Table(name = "reply_message")
public class ReplyMessageImpl extends AbstractKapuaUpdatableEntity implements ReplyMessage {
    
 
  /**
   * .
   */
  private static final long serialVersionUID = 3580907090431701286L;



  @Basic
  @Column(name = "types", nullable = false)
    private String types;
    
    

  @Basic
  @Column(name = "keywords", nullable = false)
    private String keywords;
    
  @Basic
  @Column(name = "content", nullable = false)
    private String content;
    
  @Basic
  @Column(name = "title", nullable = false)
    private String title;
    
    
  @Basic
  @Column(name = "description", nullable = false)
    private String description;
    
    
  @Basic
  @Column(name = "picurl", nullable = false)
    private String picurl;
    
    
  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "eid", column = @Column(name = "processrecovery_id"))
  })
  private KapuaEid processrecoveryId;
    
    
  @Basic
  @Column(name = "url", nullable = false)
    private String url;
    
    

  

    
  /**
   * Constructor.
   */
  protected ReplyMessageImpl() {
      super();
  }
  
  /**
   * Constructor
   *
   * @param scopeId.
   */
  public ReplyMessageImpl(KapuaId scopeId) {
      super(scopeId);
    
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }



  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPicurl() {
    return picurl;
  }

  public void setPicurl(String picurl) {
    this.picurl = picurl;
  }

  public KapuaId getProcessrecoveryId() {
    return processrecoveryId;
  }

  public void setProcessrecoveryId(KapuaId processrecoveryId) {
    this.processrecoveryId = processrecoveryId != null ? (processrecoveryId instanceof KapuaEid
           ? (KapuaEid) processrecoveryId : new KapuaEid(processrecoveryId)) : null;
  }
  
  

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }




  
  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }



 
    




}
