package org.eclipse.kapua.service.apkInfo.internal;


import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.apkInfo.ApkInfo;
import org.eclipse.kapua.service.apkInfo.ApkInfoService;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.authorization.domain.Domain;

import javax.persistence.Query;

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
public class ApkInfoServiceImpl extends AbstractKapuaService implements ApkInfoService {

    private static final Domain APKINFO_DOMAIN = new ApkInfoDomain();

    public ApkInfoServiceImpl() {
        super(ApkInfoEntityManagerFactory.getInstance());
    }

  
 
   



	@Override
	public ApkInfo findById(KapuaId apkInfoId,KapuaId scopeId) throws KapuaException {
		// TODO Auto-generated method stub
		 ArgumentValidator.notNull(apkInfoId, "apkInfoId");
		  ArgumentValidator.notNull(scopeId, "scopeId");
		 //checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	        authorizationService.checkPermission(permissionFactory.newPermission(APKINFO_DOMAIN, Actions.read, scopeId));
	        return entityManagerSession.onResult(em -> (ApkInfo)ApkInfoDAO.find(em,apkInfoId));
	}







	@Override
	public ApkInfo findByPackagename(KapuaId scopeId, String packagename)
			throws KapuaException {
		// TODO Auto-generated method stub
		 ArgumentValidator.notNull(scopeId, "scopeId");
		 ArgumentValidator.notNull(packagename, "packagename");
		//checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	        authorizationService.checkPermission(permissionFactory.newPermission(APKINFO_DOMAIN, Actions.read, scopeId));
		 
	        return entityManagerSession.onResult(em -> {
	    	    Query  q;
	            q = em.createNamedQuery("ApkInfo.queryByPackagename",ApkInfo.class);
	            q.setParameter(1,packagename);
	            ApkInfo  apkInfo=(ApkInfo) q.getResultList().get(0);
	  		 
	           
	            return apkInfo;
	        });
	}






 //queryByDistinct
	@Override
	public ApkInfo findByDistinct(KapuaId scopeId, String packagename,
			String forversion) throws KapuaException {
		// TODO Auto-generated method stub
		ArgumentValidator.notNull(scopeId, "scopeId");
		 ArgumentValidator.notNull(packagename, "packagename");
		 ArgumentValidator.notNull(forversion, "forversion");
		 
		//checkAccess
		 KapuaLocator locator = KapuaLocator.getInstance();
	        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
	        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
	        authorizationService.checkPermission(permissionFactory.newPermission(APKINFO_DOMAIN, Actions.read, scopeId));
	        
	        
	        
	        return entityManagerSession.onResult(em -> {
	    	    Query  q;
	            q = em.createNamedQuery("ApkInfo.queryByDistinct",ApkInfo.class);
	            q.setParameter(1,packagename);
	            q.setParameter(2,forversion);
	            ApkInfo  apkInfo=(ApkInfo) q.getResultList().get(0);
	  		 
	           
	            return apkInfo;
	        });
	}
     
     
  








}
