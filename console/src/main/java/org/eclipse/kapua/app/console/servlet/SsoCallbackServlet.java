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
 *     Red Hat Inc
 *******************************************************************************/
package org.eclipse.kapua.app.console.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.json.JsonObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;
import org.apache.shiro.ShiroException;
import org.eclipse.kapua.app.console.server.util.KapuaExceptionHandler;
import org.eclipse.kapua.app.console.server.util.SsoHelper;
import org.eclipse.kapua.app.console.server.util.SsoLocator;
import org.eclipse.kapua.app.console.shared.GwtKapuaException;
import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.authentication.shiro.JwtCredentialsImpl;
import org.eclipse.kapua.service.authentication.shiro.realm.JwtHelper;
import org.eclipse.kapua.service.user.User;
import org.eclipse.kapua.service.user.UserCreator;
import org.eclipse.kapua.service.user.UserFactory;
import org.eclipse.kapua.service.user.UserService;
import org.eclipse.kapua.service.user.UserType;
import org.eclipse.kapua.sso.SingleSignOnLocator;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtContext;

public class SsoCallbackServlet extends HttpServlet {

    private static final long serialVersionUID = -4854037814597039013L;

    private SingleSignOnLocator locator;
    private boolean userExist = true;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.locator = SsoLocator.getLocator(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        final String authCode = req.getParameter("code");
        System.out.println("authCode:"+authCode);
        
        final URI redirectUri = SsoHelper.getRedirectUri();
        System.out.println("redirectUri:"+redirectUri);
        
        final JsonObject jsonObject = locator.getService().getAccessToken(authCode, redirectUri);
        System.out.println("jsonObject:"+jsonObject);
        // Get and clean jwks_uri property
        final String accessToken = jsonObject.getString("access_token");
        final String homeUri = SsoHelper.getHomeUri();
        System.out.println("accessToken:"+accessToken);
        System.out.println("homeUri:"+homeUri);
        
        //check wether the user is in the table 
        String jwt =new JwtCredentialsImpl( accessToken).getJwt();
        
        final String name;
        JwtContext ctx;
        try {
            ctx = JwtHelper.processJwt(jwt);
            System.out.println("getJwtClaims:"+ctx.getJwtClaims().toJson());
            name = ctx.getJwtClaims().getClaimValue("preferred_username", String.class);
        } catch (MalformedClaimException | InvalidJwtException e) {
            throw new ShiroException("Failed to parse JWT", e);
        }

        if (name == null || name.isEmpty()) {
            throw new ShiroException("'preferred_username' missing on JWT");
        }
        System.out.println("name:"+name);
        try {
            userExist = userExist(name);
        } catch (GwtKapuaException e1) {
            e1.printStackTrace();
            System.out.println("判断用户是否存在异常");
        }
        
        //if not exist ,then to create a user
        if(!userExist){
            try {
                addUser(ctx.getJwtClaims());
            } catch (GwtKapuaException | MalformedClaimException e) {
                e.printStackTrace();
                System.out.println("添加用户出现异常");
            } 
        }

        try {
            final URIBuilder redirect = new URIBuilder(homeUri);
            redirect.addParameter("access_token", accessToken);
            resp.sendRedirect(redirect.toString());
        } catch (final URISyntaxException e) {
            throw new ServletException("Failed to parse redirect URL: " + homeUri, e);
        }
    }
    
    public boolean userExist(String name) throws GwtKapuaException{
        User user = null;
        try {
            System.out.println("enter userExist...");
            KapuaLocator locator = KapuaLocator.getInstance();
            System.out.println("locator:"+locator.hashCode());
            UserService userService = locator.getService(UserService.class);
            System.out.println("userService:"+userService.getConfigMetadata());
            user = userService.findByName(name);
            System.out.println("userName:"+user.getDisplayName());
        } catch (Throwable t) {
            KapuaExceptionHandler.handle(t);
        }
        return user!=null;
    }
    
    private void addUser(JwtClaims claims) throws GwtKapuaException, MalformedClaimException{
        String scopeid = "AQ";
        String username= claims.getClaimValue("preferred_username", String.class);
        String displayName = claims.getClaimValue("name", String.class);
        String email =  claims.getClaimValue("email", String.class);
        String externalId =  claims.getClaimValue("sub", String.class);
        
        System.out.println("username:"+username+" displayName:"+displayName+" email:"+email+" externalId:"+externalId);
        //String phoneNumber=
        try {
            KapuaLocator locator = KapuaLocator.getInstance();
            UserFactory userFactory = locator.getFactory(UserFactory.class);
    
            KapuaId scopeId = KapuaEid.parseCompactId(scopeid);
            UserCreator userCreator = userFactory.newCreator(scopeId,username);
            userCreator.setDisplayName(displayName);
            userCreator.setEmail(email);
            userCreator.setUserType(UserType.EXTERNAL);
            userCreator.setExternalId(externalId);
            //userCreator.setPhoneNumber(gwtUserCreator.getPhoneNumber());
    
            //
            // Create the User
            UserService userService = locator.getService(UserService.class);
            User user = userService.create(userCreator);
            
            System.out.println("user displayName in addUser:"+user.getDisplayName());
        } catch (Throwable t) {
            KapuaExceptionHandler.handle(t);
        }
    }
    
}
