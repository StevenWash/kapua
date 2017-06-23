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
package org.eclipse.kapua.app.api.v1.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.app.api.v1.resources.model.CountResult;
import org.eclipse.kapua.app.api.v1.resources.model.EntityId;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.commons.model.query.predicate.AndPredicate;
import org.eclipse.kapua.commons.model.query.predicate.AttributePredicate;
import org.eclipse.kapua.locator.KapuaLocator;


import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.authorization.domain.Domain;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.weather.internal.NormalResult;
import org.eclipse.kapua.service.weather.BaseIpService;
import org.eclipse.kapua.service.weather.internal.IpDomain;
import org.eclipse.kapua.service.weather.internal.SinaIpService;
import org.eclipse.kapua.service.weather.internal.SinaIpInfo;





import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("IpAPI")
@Path("/ipApi") 
public class IpApi{

	 private static final Domain IP_DOMAIN = new IpDomain();
	
    /**
     * Gets the {@link AccessInfo} specified by the "accessInfoId" path parameter.
     *
     * @param scopeId
     *            The {@link ScopeId} of the requested {@link AccessInfo}.
     * @param accessInfoId
     *            The id of the requested {@link AccessInfo}.
     * @return The requested {@link AccessInfo} object.
     * @throws KapuaException 
     * @since 1.0.0
     */

	@Context HttpServletRequest request;
    //getAreaByIp
    @GET
    @Path("/{ip}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an area by IP", //
            notes = "Gets the Area specified by the ip  parameter", //
            response = String.class)
    public String getAreaByIp(
             @ApiParam(value = "The ip of the requested Area", required = true) @PathParam("ip") String ip) throws KapuaException 
            {
    	 KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        
		 authorizationService.checkPermission(permissionFactory.newPermission(IP_DOMAIN, Actions.read,  KapuaId.ANY));

		

         
    	
		   NormalResult result = new NormalResult();
		   BaseIpService ipService = new SinaIpService();
		   String city=null;
		   try {
           if(ip!=null&&!ip.equals("")){
        		String httpResult = ipService.getInformation(ip);
				SinaIpInfo ipInfo = new SinaIpInfo();
				ipInfo.doParser(httpResult);
				city = ipInfo.getCity();
			   }
		   }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
           return city;


     }
    
    
    @GET
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Gets an area by IP", //
            notes = "Gets the Area specified by the ip  parameter", //
            response = String.class)
    public String getArea()
    		throws KapuaException {
           System.out.println("sdfsdf");
         String ip=null;
    	 KapuaLocator locator = KapuaLocator.getInstance();
         AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
         PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
        
		 authorizationService.checkPermission(permissionFactory.newPermission(IP_DOMAIN, Actions.read,  KapuaId.ANY));
		 
		
		  try {
			ip = request.getHeader("x-forwarded-for");  
			  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			      ip = request.getHeader("Proxy-Client-IP");  
			  }  
			  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			      ip = request.getHeader("WL-Proxy-Client-IP");  
			  }  
			  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			      ip = request.getHeader("HTTP_CLIENT_IP");  
			  }  
			  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			      ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
			  }  
			  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			      ip = request.getRemoteAddr();  
			  }
			 
			  System.out.println(ip+"<<<<<<<<<<<<<<<<");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    	
		   

		  return ip;
     }
    
}
