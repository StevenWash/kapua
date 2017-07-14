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
package org.eclipse.kapua.app.console.server;

import java.net.URI;
import java.util.UUID;

import org.eclipse.kapua.app.console.server.util.SsoHelper;
import org.eclipse.kapua.app.console.server.util.SsoLocator;
import org.eclipse.kapua.app.console.setting.ConsoleSetting;
import org.eclipse.kapua.app.console.setting.ConsoleSettingKeys;
import org.eclipse.kapua.app.console.shared.model.GwtLoginInformation;
import org.eclipse.kapua.app.console.shared.service.GwtSettingsService;
import org.eclipse.kapua.sso.SingleSignOnLocator;
import org.eclipse.kapua.sso.SingleSignOnService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * This is the security token service, a concrete implementation to fix the XSFR security problem.
 */
public class GwtSettingsServiceImpl extends RemoteServiceServlet implements GwtSettingsService {

    private static final long serialVersionUID = -6876999298300071273L;
    private static final ConsoleSetting SETTINGS = ConsoleSetting.getInstance();

    @Override
    public GwtLoginInformation getLoginInformation() {
        final GwtLoginInformation result = new GwtLoginInformation();
        result.setBackgroundCredits(SETTINGS.getString(ConsoleSettingKeys.LOGIN_BACKGROUND_CREDITS));
        result.setInformationSnippet(SETTINGS.getString(ConsoleSettingKeys.LOGIN_GENERIC_SNIPPET));
        return result;
    }

    @Override
    public String getSsoLoginUri() {
        URI uri= SsoHelper.getRedirectUri();
        System.out.println("uri:"+uri);
        SingleSignOnLocator lo=SsoLocator.getLocator(this);
        System.out.println("lo:"+lo);
        SingleSignOnService  ss=lo.getService();
        System.out.println("ss:"+ss.toString());
        String loginUri=ss.getLoginUri(UUID.randomUUID().toString(), uri);
        System.out.println("loginUri:"+loginUri);
        return loginUri;
    }

    @Override
    public boolean getSsoEnabled() {
        return SsoLocator.getLocator(this).getService().isEnabled();
    }

    @Override
    public String getHomeUri() {
        return SETTINGS.getString(ConsoleSettingKeys.SITE_HOME_URI);
    }
}
