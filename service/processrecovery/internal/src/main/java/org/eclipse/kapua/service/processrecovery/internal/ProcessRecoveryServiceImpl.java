package org.eclipse.kapua.service.processrecovery.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;

import org.eclipse.kapua.KapuaException;
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
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryCreator;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryFactory;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryListResult;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryQuery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryService;




/**
 * {@link ProcessRecoveryService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class ProcessRecoveryServiceImpl extends AbstractKapuaConfigurableResourceLimitedService<
     ProcessRecovery, ProcessRecoveryCreator, ProcessRecoveryService, ProcessRecoveryListResult, 
       ProcessRecoveryQuery,
      ProcessRecoveryFactory> implements ProcessRecoveryService {

  private static final Domain PROCESSRECOVERY_DOMAIN = new ProcessRecoveryDomain();
  
  /**
   * .
   */
  public ProcessRecoveryServiceImpl() {
    super(ProcessRecoveryService.class.getName(), PROCESSRECOVERY_DOMAIN, 
          ProcessRecoveryEntityManagerFactory
          .getInstance(), ProcessRecoveryService.class, ProcessRecoveryFactory.class);
  }

  
  @Override
  public ProcessRecovery findById(KapuaId processRecoveryId,KapuaId scopeId) throws KapuaException {
    ArgumentValidator.notNull(processRecoveryId, "processRecoveryId");
    ArgumentValidator.notNull(scopeId, "scopeId");
    //checkAccess
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(PROCESSRECOVERY_DOMAIN, 
        Actions.read, scopeId));
    return entityManagerSession.onResult(em -> (ProcessRecovery)
          ProcessRecoveryDao.find(em,processRecoveryId));
  }






  @Override
  public ProcessRecovery create(ProcessRecoveryCreator processRecoveryCreator)
        throws KapuaException {
    // TODO Auto-generated method stub
   
    ArgumentValidator.notNull(processRecoveryCreator, "processRecoveryCreator");
    
    KapuaLocator locator = KapuaLocator.getInstance();
  
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
  
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
   
    authorizationService.checkPermission(permissionFactory.newPermission(PROCESSRECOVERY_DOMAIN,
        Actions.write, processRecoveryCreator.getScopeId()));
   
    System.out.println("---------------ProcessRecoveryServiceImpl-------------");
    
    return entityManagerSession.onTransactedInsert(em -> 
     
      ProcessRecoveryDao.create(em, processRecoveryCreator)
     
    );
    
    
  }
     
  @Override  
  public void delete(KapuaId scopeId, KapuaId processRecoveryId)throws KapuaException {
    
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(processRecoveryId, "processRecoveryId");
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(PROCESSRECOVERY_DOMAIN,
        Actions.delete, scopeId));
    
    entityManagerSession.onTransactedAction(em -> {
      
      ProcessRecovery processRecoveryx = ProcessRecoveryDao.find(em, processRecoveryId);
      if (processRecoveryx == null) {
        throw new KapuaEntityNotFoundException(ProcessRecovery.TYPE, processRecoveryId);
      }

      ProcessRecoveryDao.delete(em, processRecoveryId);
      
    });
    
  }


  @Override
  public ProcessRecovery update(ProcessRecovery processRecovery) throws KapuaException {
    // TODO Auto-generated method stub
    ArgumentValidator.notNull(processRecovery.getId(), "processRecovery.id");
    
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(PROCESSRECOVERY_DOMAIN,
        Actions.read, processRecovery.getScopeId()));
    
    
    return entityManagerSession.onTransactedResult(em -> {
      ProcessRecovery currentProcessRecovery = ProcessRecoveryDao.find(em, processRecovery.getId());
      if (currentProcessRecovery == null) {
        throw new KapuaEntityNotFoundException(ProcessRecovery.TYPE, processRecovery.getId());
      }
      currentProcessRecovery.setNextId(processRecovery.getNextId());
      currentProcessRecovery.setParentId(processRecovery.getParentId());
      currentProcessRecovery.setDataContent(processRecovery.getDataContent());
      currentProcessRecovery.setDataTitle(processRecovery.getDataTitle());
      

      // Update
      return ProcessRecoveryDao.update(em, currentProcessRecovery);
    });
  }


  @Override
  public ProcessRecovery find(KapuaId scopeId, KapuaId entityId) throws KapuaException {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public KapuaListResult<ProcessRecovery> query(KapuaQuery<ProcessRecovery> query)
        throws KapuaException {
    // TODO Auto-generated method stub
    //
    // Check Access
   

    return entityManagerSession.onResult(em -> ProcessRecoveryDao.query(em, query));
  }


  @Override
  public long count(KapuaQuery<ProcessRecovery> query) throws KapuaException {
    // TODO Auto-generated method stub
    return (long) entityManagerSession.onResult(entityManager -> 
      ProcessRecoveryDao.count(entityManager, query));
  }



}
