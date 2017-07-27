package org.eclipse.kapua.service.appversion.internal;

import java.util.Objects;

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
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;
import org.eclipse.kapua.service.appversion.AppVersionFactory;
import org.eclipse.kapua.service.appversion.AppVersionListResult;
import org.eclipse.kapua.service.appversion.AppVersionQuery;
import org.eclipse.kapua.service.appversion.AppVersionService;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;




/**
 * {@link AppVersionService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class AppVersionServiceImpl extends AbstractKapuaConfigurableResourceLimitedService<
         AppVersion, AppVersionCreator, AppVersionService, AppVersionListResult, AppVersionQuery,
            AppVersionFactory> implements AppVersionService {

  private static final Domain APPVERSION_DOMAIN = new AppVersionDomain();

  public AppVersionServiceImpl() {
    super(AppVersionService.class.getName(), APPVERSION_DOMAIN, AppVersionEntityManagerFactory
          .getInstance(), AppVersionService.class, AppVersionFactory.class);
  }

  
  @Override
  public AppVersion findById(KapuaId scopeId,KapuaId appVersionId) throws KapuaException {
   
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(appVersionId, "appVersionId");
    //checkAccess
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN, 
        Actions.read, scopeId));
    return entityManagerSession.onResult(em -> (AppVersion)AppVersionDao.find(em,appVersionId));
  }







  @Override
 public AppVersion findByPackagename(KapuaId scopeId, String packagename)
     throws KapuaException {
    // TODO Auto-generated method stub
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(packagename, "packagename");
    //checkAccess
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN,
         Actions.read, scopeId));

    return entityManagerSession.onResult(em -> {
      TypedQuery<AppVersion>  q;
      q = em.createNamedQuery("AppVersion.queryByPackagename",AppVersion.class);
      q.setParameter(1,packagename);
      AppVersion  appVersion = (AppVersion) q.getResultList().get(0);
 
     
      return appVersion;
    });
  }







  @Override
   public AppVersion findByDistinct(KapuaId scopeId, String packagename,
      String forversion) throws KapuaException {
    
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(packagename, "packagename");
    ArgumentValidator.notNull(forversion, "forversion");
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN,
        Actions.read, scopeId));
    return entityManagerSession.onResult(em -> {
      TypedQuery<AppVersion>  q;
      q = em.createNamedQuery("AppVersion.queryByDistinct",AppVersion.class);
      q.setParameter(1,packagename);
      q.setParameter(2,forversion);
      AppVersion appVersion = (AppVersion) q.getResultList().get(0);
      return appVersion;
    });
  }


  @Override
  public AppVersion create(AppVersionCreator appVersionCreator)throws KapuaException {
    // TODO Auto-generated method stub
   
    ArgumentValidator.notNull(appVersionCreator, "appVersionCreator");
    
    KapuaLocator locator = KapuaLocator.getInstance();
  
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
  
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
   
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN,
        Actions.read, appVersionCreator.getScopeId()));
   
   
    
    return entityManagerSession.onTransactedInsert(em -> 
     
      AppVersionDao.create(em, appVersionCreator)
     
    );
    
    
  }
     
  @Override  
  public void delete(KapuaId scopeId, KapuaId appVersionId)throws KapuaException {
    
    ArgumentValidator.notNull(scopeId, "scopeId");
    ArgumentValidator.notNull(appVersionId, "appVersionId");
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN,
        Actions.read, scopeId));
    
    entityManagerSession.onTransactedAction(em -> {
      
      AppVersion appVersionx = AppVersionDao.find(em, appVersionId);
      if (appVersionx == null) {
        throw new KapuaEntityNotFoundException(AppVersion.TYPE, appVersionId);
      }

      AppVersionDao.delete(em, appVersionId);
      
    });
    
  }


  @Override
  public AppVersion update(AppVersion appVersion) throws KapuaException {
    // TODO Auto-generated method stub
    System.out.println("appVersionServiceImpl-----update");
    ArgumentValidator.notNull(appVersion.getId(), "appVersion.id");
    
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN,
        Actions.read, appVersion.getScopeId()));
    
    
    return entityManagerSession.onTransactedResult(em -> {
      AppVersion currentAppVersion = AppVersionDao.find(em, appVersion.getId());
      if (currentAppVersion == null) {
        throw new KapuaEntityNotFoundException(AppVersion.TYPE, appVersion.getId());
      }
      if (!Objects.equals(currentAppVersion.getScopeId(), appVersion.getScopeId())) {
        throw new KapuaAppVersionException(KapuaAppVersionErrorCodes.ILLEGAL_ARGUMENT, 
            null, "appVersion.scopeId");
      }
      System.out.println("---------------------");
      
      currentAppVersion.setPackagename(appVersion.getPackagename());
      currentAppVersion.setCode(appVersion.getCode());
      currentAppVersion.setVersion(appVersion.getVersion());
      currentAppVersion.setMd5(appVersion.getMd5());
      currentAppVersion.setForversion(appVersion.getForversion());
      currentAppVersion.setSize(appVersion.getSize());
      currentAppVersion.setTypes(appVersion.getTypes());
      currentAppVersion.setUrl(appVersion.getUrl());
      currentAppVersion.setRevision(appVersion.getRevision());
      
      // Update
      return AppVersionDao.update(em, currentAppVersion);
    });
  }


  @Override
  public AppVersion find(KapuaId scopeId, KapuaId entityId) throws KapuaException {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public KapuaListResult<AppVersion> query(KapuaQuery<AppVersion> query) throws KapuaException {
    // TODO Auto-generated method stub
    //
    // Check Access
   

    return entityManagerSession.onResult(em -> AppVersionDao.query(em, query));
  }


  @Override
  public long count(KapuaQuery<AppVersion> query) throws KapuaException {
    // TODO Auto-generated method stub
    return (long) entityManagerSession.onResult(entityManager -> 
      AppVersionDao.count(entityManager, query));
  }



}
