package org.eclipse.kapua.service.appversion.internal;

import java.util.Objects;

import javax.persistence.TypedQuery;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;
import org.eclipse.kapua.service.appversion.AppVersionService;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;



/**
 * {@link weatherService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class AppVersionServiceImpl extends AbstractKapuaService implements AppVersionService {

  private static final Domain APPVERSION_DOMAIN = new AppVersionDomain();

  public AppVersionServiceImpl() {
        super(AppVersionEntityManagerFactory.getInstance());
  }

  
  @Override
  public AppVersion findById(KapuaId appVersionId,KapuaId scopeId) throws KapuaException {
    ArgumentValidator.notNull(appVersionId, "appVersionId");
    ArgumentValidator.notNull(scopeId, "scopeId");
    //checkAccess
    KapuaLocator locator = KapuaLocator.getInstance();
    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
    authorizationService.checkPermission(permissionFactory.newPermission(APPVERSION_DOMAIN, 
        Actions.read, scopeId));
    return entityManagerSession.onResult(em -> (AppVersion)AppVersionDAO.find(em,appVersionId));
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
      System.out.println("packagename:--" + packagename);
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
    System.out.println("appversionServiceImpl --- create");
    return entityManagerSession.onTransactedInsert(em -> {
      AppVersion appVersion = null;
      appVersion = AppVersionDAO.create(em, appVersionCreator);
      em.persist(appVersion);
      return appVersion;
      
    });
    
    
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
      
      AppVersion appVersionx = AppVersionDAO.find(em, appVersionId);
      if (appVersionx == null) {
        throw new KapuaEntityNotFoundException(AppVersion.TYPE, appVersionId);
      }

      AppVersionDAO.delete(em, appVersionId);
      
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
      System.out.println("______________)))))))))");
      AppVersion currentVersion = AppVersionDAO.find(em, appVersion.getId());
      System.out.println("*************");
      if (currentVersion == null) {
        throw new KapuaEntityNotFoundException(AppVersion.TYPE, appVersion.getId());
      }
      /* if (!Objects.equals(currentVersion.getScopeId(), appVersion.getScopeId())) {
        throw new KapuaAppVersionException(KapuaAppVersionErrorCodes.ILLEGAL_ARGUMENT, 
            null, "appVersion.scopeId");
      }*/
      System.out.println("---------------------");
      
      currentVersion.setPackagename(appVersion.getPackagename());
      currentVersion.setCode(appVersion.getCode());
      currentVersion.setVersion(appVersion.getVersion());
      currentVersion.setMd5(appVersion.getMd5());
      currentVersion.setForversion(appVersion.getForversion());
      currentVersion.setSize(appVersion.getSize());
      currentVersion.setTypes(appVersion.getTypes());
      currentVersion.setUrl(appVersion.getUrl());
      currentVersion.setRevision(appVersion.getRevision());
      
      // Update
      return AppVersionDAO.update(em, currentVersion);
    });
  }



}
