/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.sso.provider.setting;

public class SSOConfig {
    
    public static String tokenUri="https://dev.izhiju.cn/auth/realms/master/protocol/openid-connect/token";
    
    public static String clientId="console";
    
    public static String grantType="authorization_code";
    
    public static String clientSecret="c9f4d3e4-c0b8-4606-826c-1de1debaab25";
    
    private  SSOConfig() {
    }
}
