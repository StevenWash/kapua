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
  private static final long serialVersionUID = -5826200641506486043L;



  @Basic
  @Column(name = "types", nullable = false)
    private String types;
    
    

  @Basic
  @Column(name = "content", nullable = false)
    private String content;
    
  @Basic
  @Column(name = "title", nullable = false)
    private String title;
    
  @Basic
  @Column(name = "keywords", nullable = false)
    private String keywords;
  
  @Basic
  @Column(name = "description", nullable = false)
    private String description;
  
  
  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "eid", column = @Column(name = "processrecovery_id"))
  })
  protected KapuaEid processrecoveryId;
  
  
  @Basic
  @Column(name = "picurl", nullable = false)
    private String picurl;
  
  
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

  /**.
   * @return the types.
   */
  public String getTypes() {
    return types;
  }

  /**.
   * @param types the types to set.
   */
  public void setTypes(String types) {
    this.types = types;
  }

  /**.
   * @return the content.
   */
  public String getContent() {
    return content;
  }

  /**.
   * @param content the content to set.
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**.
   * @return the title.
   */
  public String getTitle() {
    return title;
  }

  /**.
   * @param title the title to set.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**.
   * @return the keywords.
   */
  public String getKeywords() {
    return keywords;
  }

  /**.
   * @param keywords the keywords to set.
   */
  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  /**.
   * @return the description.
   */
  public String getDescription() {
    return description;
  }

  /**.
   * @param description the description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**.
   * @return the processrecoveryId.
   */
  public KapuaEid getProcessrecoveryId() {
    return processrecoveryId;
  }

  /**.
   * @param processrecoveryId the processrecoveryId to set.
   */
  public void setProcessrecoveryId(KapuaId processrecoveryId) {
    this.processrecoveryId = processrecoveryId != null ? (processrecoveryId instanceof KapuaEid ? (KapuaEid) processrecoveryId : new KapuaEid(processrecoveryId)) : null;
  }

  /**.
   * @return the picurl.
   */
  public String getPicurl() {
    return picurl;
  }

  /**.
   * @param picurl the picurl to set.
   */
  public void setPicurl(String picurl) {
    this.picurl = picurl;
  }

  /**.
   * @return the url.
   */
  public String getUrl() {
    return url;
  }

  /**.
   * @param url the url to set.
   */
  public void setUrl(String url) {
    this.url = url;
  }




}
