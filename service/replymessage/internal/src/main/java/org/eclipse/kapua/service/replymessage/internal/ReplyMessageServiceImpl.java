package org.eclipse.kapua.service.replymessage.internal;

import javax.persistence.TypedQuery;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.commons.configuration.AbstractKapuaConfigurableResourceLimitedService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaListResult;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.replymessage.ReplyMessage;
import org.eclipse.kapua.service.replymessage.ReplyMessageCreator;
import org.eclipse.kapua.service.replymessage.ReplyMessageFactory;
import org.eclipse.kapua.service.replymessage.ReplyMessageListResult;
import org.eclipse.kapua.service.replymessage.ReplyMessageQuery;
import org.eclipse.kapua.service.replymessage.ReplyMessageService;




/**
 * {@link replyMessageService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class ReplyMessageServiceImpl extends AbstractKapuaConfigurableResourceLimitedService<
      ReplyMessage, ReplyMessageCreator, ReplyMessageService, ReplyMessageListResult, ReplyMessageQuery,
      ReplyMessageFactory> implements ReplyMessageService {

  private static final Domain REPLYMESSAGE_DOMAIN = new ReplyMessageDomain();

  public ReplyMessageServiceImpl() {
    super(ReplyMessageService.class.getName(), REPLYMESSAGE_DOMAIN, ReplyMessageEntityManagerFactory
          .getInstance(), ReplyMessageService.class, ReplyMessageFactory.class);
  }

  
  @Override
  public ReplyMessage findById(KapuaId replyMessageId,KapuaId scopeId) throws KapuaException {
    ArgumentValidator.notNull(replyMessageId, "replyMessageId");
    ArgumentValidator.notNull(scopeId, "scopeId");
    //checkAccess
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(REPLYMESSAGE_DOMAIN, 
        Actions.read, scopeId));
    return entityManagerSession.onResult(em -> (ReplyMessage)
          ReplyMessageDao.find(em,replyMessageId));
  }






  @Override
  public ReplyMessage create(ReplyMessageCreator replyMessageCreator)throws KapuaException {
    // TODO Auto-generated method stub
   
    ArgumentValidator.notNull(replyMessageCreator, "replyMessageCreator");
    
    KapuaLocator locator = KapuaLocator.getInstance();
  
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
  
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
   
    authorizationService.checkPermission(permissionFactory.newPermission(REPLYMESSAGE_DOMAIN,
        Actions.write, replyMessageCreator.getScopeId()));
   
    System.out.println("---------------replyMessageServiceImpl-------------");
    
    return entityManagerSession.onTransactedInsert(em -> 
     
      ReplyMessageDao.create(em, replyMessageCreator)
     
    );
    
    
  }
     
  @Override  
  public void delete(KapuaId scopeId, KapuaId replyMessageId)throws KapuaException {
    
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(replyMessageId, "replyMessageId");
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(REPLYMESSAGE_DOMAIN,
        Actions.delete, scopeId));
    
    entityManagerSession.onTransactedAction(em -> {
      
      ReplyMessage replyMessagex = ReplyMessageDao.find(em, replyMessageId);
      if (replyMessagex == null) {
        throw new KapuaEntityNotFoundException(ReplyMessage.TYPE, replyMessageId);
      }

      ReplyMessageDao.delete(em, replyMessageId);
      
    });
    
  }


  @Override
  public ReplyMessage update(ReplyMessage replyMessage) throws KapuaException {
    // TODO Auto-generated method stub
    ArgumentValidator.notNull(replyMessage.getId(), "replyMessage.id");
    
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(REPLYMESSAGE_DOMAIN,
        Actions.read, replyMessage.getScopeId()));
    
    
    return entityManagerSession.onTransactedResult(em -> {
      ReplyMessage currentReplyMessage = ReplyMessageDao.find(em, replyMessage.getId());
      if (currentReplyMessage == null) {
        throw new KapuaEntityNotFoundException(ReplyMessage.TYPE, replyMessage.getId());
      }
     
      
      currentReplyMessage.setTitle(replyMessage.getTitle());
      currentReplyMessage.setTypes(replyMessage.getTypes());
      currentReplyMessage.setPicurl(replyMessage.getPicurl());
      currentReplyMessage.setUrl(replyMessage.getUrl());
      currentReplyMessage.setDescription(replyMessage.getDescription());
      currentReplyMessage.setProcessrecoveryId(replyMessage.getProcessrecoveryId());
      currentReplyMessage.setPicurl(replyMessage.getPicurl());
      currentReplyMessage.setKeywords(replyMessage.getKeywords());
      currentReplyMessage.setContent(replyMessage.getContent());
      
      // Update
      return ReplyMessageDao.update(em, currentReplyMessage);
    });
  }


  @Override
  public ReplyMessage find(KapuaId scopeId, KapuaId entityId) throws KapuaException {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public KapuaListResult<ReplyMessage> query(KapuaQuery<ReplyMessage> query) throws KapuaException {
    // TODO Auto-generated method stub
    //
    // Check Access
   

    return entityManagerSession.onResult(em -> ReplyMessageDao.query(em, query));
  }


  @Override
  public long count(KapuaQuery<ReplyMessage> query) throws KapuaException {
    // TODO Auto-generated method stub
    return (long) entityManagerSession.onResult(entityManager -> 
      ReplyMessageDao.count(entityManager, query));
  }



}
