package org.eclipse.kapua.service.appinfo.internal;

import java.util.Objects;

import javax.persistence.TypedQuery;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.configuration.AbstractKapuaConfigurableResourceLimitedService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaListResult;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoCreator;
import org.eclipse.kapua.service.appinfo.AppInfoFactory;
import org.eclipse.kapua.service.appinfo.AppInfoListResult;
import org.eclipse.kapua.service.appinfo.AppInfoQuery;
import org.eclipse.kapua.service.appinfo.AppInfoService;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;





/**
 * {@link AppInfoService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class AppInfoServiceImpl extends  AbstractKapuaConfigurableResourceLimitedService<
   AppInfo, AppInfoCreator, AppInfoService, AppInfoListResult, AppInfoQuery,
   AppInfoFactory> implements AppInfoService {

    private static final Domain APPINFO_DOMAIN = new AppInfoDomain();

    public AppInfoServiceImpl() {
    	 super(AppInfoService.class.getName(), APPINFO_DOMAIN, AppInfoEntityManagerFactory
    	          .getInstance(), AppInfoService.class, AppInfoFactory.class);
    }

  
 	public AppInfo findById(KapuaId scopeId,KapuaId appInfoId) throws KapuaException {
		// TODO Auto-generated method stub
		
		  ArgumentValidator.notNull(scopeId, "scopeId");
		  ArgumentValidator.notNull(appInfoId, "appInfoId");
		 //checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	     AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	     PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	     authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN, Actions.read, scopeId));
	        return entityManagerSession.onResult(em -> (AppInfo)AppInfoDAO.find(em,appInfoId));
	}







	@Override
	public AppInfo findByPackagename(KapuaId scopeId, String packagename)
			throws KapuaException {
		// TODO Auto-generated method stub
		 ArgumentValidator.notNull(scopeId, "scopeId");
		 ArgumentValidator.notNull(packagename, "packagename");
		//checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	     AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	     PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	     authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN, Actions.read, scopeId));
		 
	        return entityManagerSession.onResult(em -> {
	        	TypedQuery<AppInfo>  q;
	            q = em.createNamedQuery("AppInfo.queryByPackagename",AppInfo.class);
	            q.setParameter(1,packagename);
	            AppInfo  appInfo=(AppInfo) q.getResultList().get(0);
	  		 
	            return appInfo;
	        });
	}






 //queryByDistinct
	@Override
	public AppInfo findByDistinct(KapuaId scopeId, String packagename,
			String forversion) throws KapuaException {
		// TODO Auto-generated method stub
		ArgumentValidator.notNull(scopeId, "scopeId");
		ArgumentValidator.notNull(packagename, "packagename");
		ArgumentValidator.notNull(forversion, "forversion");
		 
		//checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	     AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	     PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	     authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN, Actions.read, scopeId));
	        
	       return entityManagerSession.onResult(em -> {
	        	TypedQuery<AppInfo>  q;
	    	    
	            q = em.createNamedQuery("ApkInfo.queryByDistinct",AppInfo.class);
	          
	            q.setParameter(1,packagename+".patch");
	            q.setParameter(2,forversion);
	            AppInfo  apkInfo=(AppInfo) q.getResultList().get(0);
	  		 
	           
	            return apkInfo;
	        });
	}


	@Override
	public AppInfo create(AppInfoCreator appInfoCreator) throws KapuaException {
		
	    ArgumentValidator.notNull(appInfoCreator, "appInfoCreator");
	    
	    KapuaLocator locator = KapuaLocator.getInstance();
	  
	    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	  
	    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	   
	    authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN,
	        Actions.read, appInfoCreator.getScopeId()));
	   
	   
	    
	    return entityManagerSession.onTransactedInsert(em -> 
	     
	    AppInfoDAO.create(em, appInfoCreator)
	     
	    );
	}


	@Override
	public void delete(KapuaId scopeId, KapuaId appInfoId) throws KapuaException {
		// TODO Auto-generated method stub
	    ArgumentValidator.notNull(scopeId, "scopeId");
	    ArgumentValidator.notNull(appInfoId, "appInfoId");
	    KapuaLocator locator = KapuaLocator.getInstance();
	    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	    authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN,
	        Actions.delete, scopeId));
	    
	    entityManagerSession.onTransactedAction(em -> {
	      
	      AppInfo appInfox = AppInfoDAO.find(em, appInfoId);
	      if (appInfox == null) {
	        throw new KapuaEntityNotFoundException(AppInfo.TYPE, appInfoId);
	      }

	      AppInfoDAO.delete(em, appInfoId);
	      
	    });
		
	}


	@Override
	public AppInfo update(AppInfo appInfo) throws KapuaException {
		// TODO Auto-generated method stub
		    ArgumentValidator.notNull(appInfo.getId(), "appInfo.id");
		    
		    KapuaLocator locator = KapuaLocator.getInstance();
		    AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
		    PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
		    authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN,
		        Actions.read, appInfo.getScopeId()));
		    
		    
		    return entityManagerSession.onTransactedResult(em -> {
		    	AppInfo currentAppInfo = AppInfoDAO.find(em, appInfo.getId());
		      if (currentAppInfo == null) {
		        throw new KapuaEntityNotFoundException(AppInfo.TYPE, appInfo.getId());
		      }
		      if (!Objects.equals(currentAppInfo.getScopeId(), appInfo.getScopeId())) {
		        throw new KapuaAppInfoException(KapuaAppInfoErrorCodes.ILLEGAL_ARGUMENT, 
		            null, "appInfo.scopeId");
		      }
		      System.out.println("---------------------");
		      
		      currentAppInfo.setName(appInfo.getName());
		      currentAppInfo.setTypes(appInfo.getTypes());
		      currentAppInfo.setImage(appInfo.getImage());
		      currentAppInfo.setPackagename(appInfo.getPackagename());
		      currentAppInfo.setComment(appInfo.getComment());
		      
		      return AppInfoDAO.update(em, currentAppInfo);
		    });
	}


	@Override
	public AppInfo find(KapuaId scopeId, KapuaId entityId)
			throws KapuaException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public KapuaListResult<AppInfo> query(KapuaQuery<AppInfo> query)
			throws KapuaException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long count(KapuaQuery<AppInfo> query) throws KapuaException {
		// TODO Auto-generated method stub
		return 0;
	}
     
     
  



}
