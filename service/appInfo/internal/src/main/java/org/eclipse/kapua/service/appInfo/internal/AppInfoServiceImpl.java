package org.eclipse.kapua.service.appInfo.internal;


import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appInfo.AppInfo;
import org.eclipse.kapua.service.appInfo.AppInfoService;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.authorization.domain.Domain;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;




/**
 * {@link weatherService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class AppInfoServiceImpl extends AbstractKapuaService implements AppInfoService {

    private static final Domain APPINFO_DOMAIN = new AppInfoDomain();

    public AppInfoServiceImpl() {
        super(AppInfoEntityManagerFactory.getInstance());
    }

  
 	@Override
	public AppInfo findById(KapuaId apkInfoId,KapuaId scopeId) throws KapuaException {
		// TODO Auto-generated method stub
		 ArgumentValidator.notNull(apkInfoId, "apkInfoId");
		  ArgumentValidator.notNull(scopeId, "scopeId");
		 //checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	        authorizationService.checkPermission(permissionFactory.newPermission(APPINFO_DOMAIN, Actions.read, scopeId));
	        return entityManagerSession.onResult(em -> (AppInfo)AppInfoDAO.find(em,apkInfoId));
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
     
     
  



}
