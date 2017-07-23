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

package org.eclipse.kapua.service.processrecovery.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryCreator;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryListResult;


/**
 * AppVersion DAO
 * 
 * @since 1.0
 *
 */
public class ProcessRecoveryDao {

  private ProcessRecoveryDao() {
  }



  /**
   * update the ProcessRecovery by processRecovery
   * 
   * @param em not null
   * @param processRecovery  not null
   * @return  not null.
   */
  public static ProcessRecovery update(EntityManager em, ProcessRecovery processRecovery)
            throws KapuaException {
    //
    // Update appVersion
    ProcessRecoveryImpl processRecoveryImpl = (ProcessRecoveryImpl) processRecovery;

    return ServiceDAO.update(em, ProcessRecoveryImpl.class, processRecoveryImpl);
  }

  /**
   * find the ProcessRecovery by processRecoveryId
   * 
   * @param processRecoveryId not null
  
   * @return  not null.
   */
  public static ProcessRecovery find(EntityManager em, KapuaId processRecoveryId) {
    ProcessRecovery processRecovery = null;
    try {
      processRecovery = em.find(ProcessRecoveryImpl.class, processRecoveryId);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return  processRecovery;
  }
     


  public static void delete(EntityManager em, KapuaId processRecoveryId) 
      throws KapuaEntityNotFoundException {
    ServiceDAO.delete(em, ProcessRecoveryImpl.class, processRecoveryId);
  }
  
  
  /**
   * Creates and return new AppVersion.
   * 
   */
  public static ProcessRecovery create(EntityManager em, ProcessRecoveryCreator
        processRecoveryCreator)
          throws KapuaException {
     
    // Create AppVersion
   
    ProcessRecovery processRecoveryImpl = new ProcessRecoveryImpl(
          processRecoveryCreator.getScopeId());
    
    processRecoveryImpl.setNextId(processRecoveryCreator.getNextId());
    processRecoveryImpl.setParentId(processRecoveryCreator.getParentId());
    processRecoveryImpl.setDataContent(processRecoveryCreator.getDataContent());
    
    processRecoveryImpl.setDataTitle(processRecoveryCreator.getDataTitle());
    
    
    return ServiceDAO.create(em, processRecoveryImpl);
  }
  
  
  public static long count(EntityManager em, KapuaQuery<ProcessRecovery> processRecoveryQuery)
      throws KapuaException {
    return ServiceDAO.count(em, ProcessRecovery.class,ProcessRecoveryImpl.class,
          processRecoveryQuery);
  }
  
  /**
   * 
   * @param em not null
   * @param processRecoveryQuery not null
   * @return  not null
   * @throws KapuaException not null.
   */
  public static ProcessRecoveryListResult query(EntityManager em, 
        KapuaQuery<ProcessRecovery>   processRecoveryQuery) throws KapuaException {
    
      
    return (ProcessRecoveryListResult) ServiceDAO.query(em, ProcessRecovery.class, 
          ProcessRecoveryImpl.class, new ProcessRecoveryListResultImpl(), processRecoveryQuery);
  }


}
