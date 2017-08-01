package org.eclipse.kapua.service.appversion.internal;

import javax.persistence.TypedQuery;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;
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
     
     
  



}