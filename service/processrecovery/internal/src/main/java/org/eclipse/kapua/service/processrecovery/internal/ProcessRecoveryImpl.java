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

package org.eclipse.kapua.service.processrecovery.internal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.kapua.commons.model.AbstractKapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;



/**
 * ReplyMessage entity implementation.
 *
 * @since 1.0
 */

@Entity(name = "ProcessRecovery")
@Table(name = "process_recovery")
public class ProcessRecoveryImpl extends AbstractKapuaUpdatableEntity implements ProcessRecovery {
    

  /**
   * .
   */
  private static final long serialVersionUID = -5826200641506486043L;



  @Basic
  @Column(name = "next_id", nullable = false)
    private Integer nextId;
    
    

  @Basic
  @Column(name = "parent_id", nullable = false)
    private Integer parentId;
    
  @Basic
  @Column(name = "data_title", nullable = false)
    private String dataTitle;
    
  @Basic
  @Column(name = "data_content", nullable = false)
    private String dataContent;
    
    

    

  

    
  /**
   * Constructor.
   */
  protected ProcessRecoveryImpl() {
      super();
  }
  
  /**
   * Constructor
   *
   * @param scopeId.
   */
  public ProcessRecoveryImpl(KapuaId scopeId) {
      super(scopeId);
    
  }

  /**.
   * @return the nextId.
   */
  public Integer getNextId() {
    return nextId;
  }

  /**.
   * @param nextId the nextId to set.
   */
  public void setNextId(Integer nextId) {
    this.nextId = nextId;
  }

  /**.
   * @return the parentId.
   */
  public Integer getParentId() {
    return parentId;
  }

  /**.
   * @param parentId the parentId to set.
   */
  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  /**.
   * @return the dataTitle.
   */
  public String getDataTitle() {
    return dataTitle;
  }

  /**.
   * @param dataTitle the dataTitle to set.
   */
  public void setDataTitle(String dataTitle) {
    this.dataTitle = dataTitle;
  }

  /**.
   * @return the dataContent.
   */
  public String getDataContent() {
    return dataContent;
  }

  /**
   * @param dataContent the dataContent to set.
   */
  public void setDataContent(String dataContent) {
    this.dataContent = dataContent;
  }



 


}
