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

package org.eclipse.kapua.service.replymessage.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.replymessage.ReplyMessage;
import org.eclipse.kapua.service.replymessage.ReplyMessageCreator;
import org.eclipse.kapua.service.replymessage.ReplyMessageListResult;


/**
 * AppVersion DAO
 * 
 * @since 1.0
 *
 */
public class ReplyMessageDao {

  private ReplyMessageDao() {
  }



  /**
   * update the ReplyMessage by replyMessage
   * 
   * @param em not null
   * @param replyMessage  not null
   * @return  not null.
   */
  public static ReplyMessage update(EntityManager em, ReplyMessage replyMessage)
            throws KapuaException {
    //
    // Update appVersion
    ReplyMessageImpl replyMessageImpl = (ReplyMessageImpl) replyMessage;

    return ServiceDAO.update(em, ReplyMessageImpl.class, replyMessageImpl);
  }

  /**
   * find the ReplyMessage by replyMessageId
   * 
   * @param replyMessageId not null
  
   * @return  not null.
   */
  public static ReplyMessage find(EntityManager em, KapuaId replyMessageId) {
    ReplyMessage replyMessage = null;
    try {
      replyMessage = em.find(ReplyMessageImpl.class, replyMessageId);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return  replyMessage;
  }
     


  public static void delete(EntityManager em, KapuaId accountId) 
      throws KapuaEntityNotFoundException {
    ServiceDAO.delete(em, ReplyMessageImpl.class, accountId);
  }
  
  
  /**
   * Creates and return new AppVersion.
   * 
   */
  public static ReplyMessage create(EntityManager em, ReplyMessageCreator replyMessageCreator)
          throws KapuaException {
     
    // Create AppVersion
   
    ReplyMessage replyMessageImpl = new ReplyMessageImpl(replyMessageCreator.getScopeId());
    
    replyMessageImpl.setTypes(replyMessageCreator.getTypes());
    replyMessageImpl.setTitle(replyMessageCreator.getTitle());
    replyMessageImpl.setPicurl(replyMessageCreator.getPicurl());
    replyMessageImpl.setProcessrecoveryId(replyMessageCreator.getProcessrecoveryId());
    replyMessageImpl.setKeywords(replyMessageCreator.getKeywords());
    replyMessageImpl.setDescription(replyMessageCreator.getDescription());
    replyMessageImpl.setUrl(replyMessageCreator.getUrl());
    replyMessageImpl.setContent(replyMessageCreator.getContent());
    
    return ServiceDAO.create(em, replyMessageImpl);
  }
  
  
  public static long count(EntityManager em, KapuaQuery<ReplyMessage> replyMessageQuery)
      throws KapuaException {
    return ServiceDAO.count(em, ReplyMessage.class,ReplyMessageImpl.class, replyMessageQuery);
  }
  
  
  public static ReplyMessageListResult query(EntityManager em, 
        KapuaQuery<ReplyMessage>   replyMessageQuery) throws KapuaException {
    
      
    return (ReplyMessageListResult) ServiceDAO.query(em, ReplyMessage.class, 
          ReplyMessageImpl.class, new ReplyMessageListResultImpl(), replyMessageQuery);
  }


}
