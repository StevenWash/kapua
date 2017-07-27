package org.eclipse.kapua.service.wxaccount.internal;


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
import org.eclipse.kapua.service.wxaccount.WxAccount;
import org.eclipse.kapua.service.wxaccount.WxAccountCreator;
import org.eclipse.kapua.service.wxaccount.WxAccountFactory;
import org.eclipse.kapua.service.wxaccount.WxAccountListResult;
import org.eclipse.kapua.service.wxaccount.WxAccountQuery;
import org.eclipse.kapua.service.wxaccount.WxAccountService;




/**
 * {@link wxAccountService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class WxAccountServiceImpl extends AbstractKapuaConfigurableResourceLimitedService<
       WxAccount, WxAccountCreator, WxAccountService, WxAccountListResult, WxAccountQuery,
       WxAccountFactory> implements WxAccountService {

  private static final Domain WXACCOUNT_DOMAIN = new WxAccountDomain();

  public WxAccountServiceImpl() {
    super(WxAccountService.class.getName(), WXACCOUNT_DOMAIN, WxAccountEntityManagerFactory
          .getInstance(), WxAccountService.class, WxAccountFactory.class);
  }

  
  @Override
  public WxAccount findById(KapuaId scopeId,KapuaId wxAccountId) throws KapuaException {
   
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(wxAccountId, "wxAccountId");
    //checkAccess
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(WXACCOUNT_DOMAIN, 
        Actions.read, scopeId));
    
    return entityManagerSession.onResult(em -> (WxAccount)WxAccountDao.find(em,wxAccountId));
  }




  @Override
  public WxAccount create(WxAccountCreator wxAccountCreator)throws KapuaException {
    // TODO Auto-generated method stub
   
    ArgumentValidator.notNull(wxAccountCreator, "wxAccountCreator");
    
    KapuaLocator locator = KapuaLocator.getInstance();
  
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
  
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
   
    authorizationService.checkPermission(permissionFactory.newPermission(WXACCOUNT_DOMAIN,
        Actions.read, wxAccountCreator.getScopeId()));
   
   
    
    return entityManagerSession.onTransactedInsert(em -> 
     
      WxAccountDao.create(em, wxAccountCreator)
     
    );
    
    
  }
     
  @Override  
  public void delete(KapuaId scopeId, KapuaId wxAccountId)throws KapuaException {
    
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(wxAccountId, "wxAccountId");
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(WXACCOUNT_DOMAIN,
        Actions.read, scopeId));
    
    entityManagerSession.onTransactedAction(em -> {
      
      WxAccount wxAccountx = WxAccountDao.find(em, wxAccountId);
      if (wxAccountx == null) {
        throw new KapuaEntityNotFoundException(WxAccount.TYPE, wxAccountId);
      }

      WxAccountDao.delete(em, wxAccountId);
      
    });
    
  }


  @Override
  public WxAccount update(WxAccount wxAccount) throws KapuaException {
    // TODO Auto-generated method stub
    System.out.println("wxAccountServiceImpl-----update");
    ArgumentValidator.notNull(wxAccount.getId(), "wxAccount.id");
    
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(WXACCOUNT_DOMAIN,
        Actions.read, wxAccount.getScopeId()));
    
    
    return entityManagerSession.onTransactedResult(em -> {
    	WxAccount currentwxAccount = WxAccountDao.find(em, wxAccount.getId());
      if (currentwxAccount == null) {
        throw new KapuaEntityNotFoundException(WxAccount.TYPE, wxAccount.getId());
      }
     
      System.out.println("---------------------");
      
      currentwxAccount.setUrlName(wxAccount.getUrlName());
      currentwxAccount.setWxAeskey(wxAccount.getWxAeskey());
      currentwxAccount.setWxToken(wxAccount.getWxToken());
      currentwxAccount.setWxAppsecret(wxAccount.getWxAppsecret());
      currentwxAccount.setWxAppid(wxAccount.getWxAppid());
      
      // Update
      return WxAccountDao.update(em, currentwxAccount);
    });
  }


  @Override
  public WxAccount find(KapuaId scopeId, KapuaId entityId) throws KapuaException {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public KapuaListResult<WxAccount> query(KapuaQuery<WxAccount> query) throws KapuaException {
    // TODO Auto-generated method stub
    //
    // Check Access
   

    return entityManagerSession.onResult(em -> WxAccountDao.query(em, query));
  }


  @Override
  public long count(KapuaQuery<WxAccount> query) throws KapuaException {
    // TODO Auto-generated method stub
    return (long) entityManagerSession.onResult(entityManager -> 
      WxAccountDao.count(entityManager, query));
  }



}
