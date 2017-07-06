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
package org.eclipse.kapua.service.authorization.role.shiro;

import java.math.BigInteger;
import java.util.List;
import net.sf.json.JSONArray;
import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.commons.configuration.AbstractKapuaConfigurableResourceLimitedService;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.Permission;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.authorization.role.Role;
import org.eclipse.kapua.service.authorization.role.RoleCreator;
import org.eclipse.kapua.service.authorization.role.RoleFactory;
import org.eclipse.kapua.service.authorization.role.RoleListResult;
import org.eclipse.kapua.service.authorization.role.RolePermissionCreator;
import org.eclipse.kapua.service.authorization.role.RolePermissionFactory;
import org.eclipse.kapua.service.authorization.role.RoleQuery;
import org.eclipse.kapua.service.authorization.role.RoleService;
import org.eclipse.kapua.service.authorization.shiro.AuthorizationEntityManagerFactory;
import javax.persistence.Query;



/**
 * Role service implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class RoleServiceImpl extends AbstractKapuaConfigurableResourceLimitedService<Role, RoleCreator, RoleService, RoleListResult, RoleQuery, RoleFactory> implements RoleService {

    private static final Domain ROLE_DOMAIN = new RoleDomain();

    public RoleServiceImpl() {
        super(RoleService.class.getName(), ROLE_DOMAIN, AuthorizationEntityManagerFactory.getInstance(), RoleService.class, RoleFactory.class);
    }

    @Override
    public Role create(RoleCreator roleCreator)
            throws KapuaException {
        ArgumentValidator.notNull(roleCreator, "roleCreator");
        ArgumentValidator.notEmptyOrNull(roleCreator.getName(), "roleCreator.name");
        ArgumentValidator.notNull(roleCreator.getPermissions(), "roleCreator.permissions");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.write, roleCreator.getScopeId()));

        //
        // If permission are created out of the role scope, check that the current user has the permission on the external scopeId.
        if (roleCreator.getPermissions() != null) {
            for (Permission p : roleCreator.getPermissions()) {
                if (p.getTargetScopeId() == null || !p.getTargetScopeId().equals(roleCreator.getScopeId())) {
                    authorizationService.checkPermission(p);
                }
            }
        }

        if (allowedChildEntities(roleCreator.getScopeId()) <= 0) {
            throw new KapuaIllegalArgumentException("scopeId", "max roles reached");
        }
        return entityManagerSession.onTransactedInsert(em -> {
            Role role = RoleDAO.create(em, roleCreator);

            if (!roleCreator.getPermissions().isEmpty()) {
                RolePermissionFactory rolePermissionFactory = locator.getFactory(RolePermissionFactory.class);
                for (Permission p : roleCreator.getPermissions()) {

                    RolePermissionCreator rolePermissionCreator = rolePermissionFactory.newCreator(roleCreator.getScopeId());

                    rolePermissionCreator.setRoleId(role.getId());
                    rolePermissionCreator.setPermission(p);

                    RolePermissionDAO.create(em, rolePermissionCreator);
                }
            }

            return role;
        });
    }

    @Override
    public Role update(Role role) throws KapuaException {
        ArgumentValidator.notNull(role, "role");
        ArgumentValidator.notEmptyOrNull(role.getName(), "role.name");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.write, role.getScopeId()));
        return entityManagerSession.onTransactedInsert(em -> {

            Role currentRole = RoleDAO.find(em, role.getId());
            if (currentRole == null) {
                throw new KapuaEntityNotFoundException(Role.TYPE, role.getId());
            }

            return RoleDAO.update(em, role);
        });
    }

    @Override
    public void delete(KapuaId scopeId, KapuaId roleId) throws KapuaException {
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.delete, scopeId));

        entityManagerSession.onTransactedAction(em -> {
            if (RoleDAO.find(em, roleId) == null) {
                throw new KapuaEntityNotFoundException(Role.TYPE, roleId);
            }

            RoleDAO.delete(em, roleId);
        });
    }

    @Override
    public Role find(KapuaId scopeId, KapuaId roleId)
            throws KapuaException {
        ArgumentValidator.notNull(scopeId, "accountId");
        ArgumentValidator.notNull(roleId, "roleId");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.read, scopeId));

        return entityManagerSession.onResult(em -> RoleDAO.find(em, roleId));
    }

    @Override
    public RoleListResult query(KapuaQuery<Role> query)
            throws KapuaException {
        ArgumentValidator.notNull(query, "query");
        ArgumentValidator.notNull(query.getScopeId(), "query.scopeId");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.read, query.getScopeId()));

        return entityManagerSession.onResult(em -> RoleDAO. (em, query));
    }

    @Override
    public long count(KapuaQuery<Role> query)
            throws KapuaException {
        ArgumentValidator.notNull(query, "query");
        ArgumentValidator.notNull(query.getScopeId(), "query.scopeId");

        //
        // Check Access
        KapuaLocator locator = KapuaLocator.getInstance();
        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.read, query.getScopeId()));

        return entityManagerSession.onResult(em -> RoleDAO.count(em, query));
    }


	@Override
	public  String queryRoles(KapuaId scopeId,
			KapuaId accessInfoUserId) throws KapuaException {					 
					ArgumentValidator.notNull(scopeId, "scopeId");
					
			        ArgumentValidator.notNull(accessInfoUserId, "accessInfoUserId");
			       
			     // Check Access
			        KapuaLocator locator = KapuaLocator.getInstance();
			        AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
			        PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
			        
			       authorizationService.checkPermission(permissionFactory.newPermission(ROLE_DOMAIN, Actions.read, scopeId));
					
					
				   return entityManagerSession.onResult(em -> {
						 
						 String result=null;
						
						
						BigInteger id=new BigInteger(accessInfoUserId.toString());
						
						  Query query;
						  query=em.createNativeQuery("select r.* from athz_role r where r.id in (select aRol.role_id from athz_access_role aRol where aRol.access_info_id=(select acc.id from athz_access_info acc  where acc.user_id=?1))");
						  
						  query.setParameter(1,id);
						  List roleArrayList=query.getResultList();
						
						
						JSONArray jarray=JSONArray.fromObject(roleArrayList);
						
						result=jarray.toString();
						
						/*Map<String,Object> map = null;
						JSONObject	jsonObject =null;
						String roleResult=null;
						StringBuffer buffer=new StringBuffer();
						for(int i=0;i<roleArrayList.size();i++){
							Object[] obj = (Object[]) roleArrayList.get(i);
							
							
							map=new HashMap<String,Object>();
							
							map.put("scopeId", obj[0]);
							map.put("id", obj[1]);
							map.put("created_on", obj[2].toString());
							map.put("created_by", obj[3]);
							map.put("modified_on", obj[4].toString());
							map.put("modified_by", obj[5]);
							map.put("name", obj[6]);
							map.put("optlock", obj[7]);
							map.put("attributes", obj[8]);
							map.put("properties", obj[9]);
							System.out.println("maps:      "+map);
							jsonObject = JSONObject.fromObject(map);
							 result=jsonObject.toString();
							 System.out.println("results               ="+result);
							 buffer.append(result+"\n");
						}*/
						
						return result;

						});
	}
  

}
