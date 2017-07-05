/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.apkinfo.internal.setting;

import org.eclipse.kapua.commons.setting.AbstractKapuaSetting;

/**
 * Class that offers access to account settings
 * 
 * @since 1.0
 *
 */
public class KapuaApkIfnoSetting extends AbstractKapuaSetting<KapuaApkInfoSettingKeys> {

    /**
     * Resource file from which source properties.
     * 
     */
    private static final String ACCOUNT_CONFIG_RESOURCE = "kapua-account-setting.properties";

    private static final KapuaApkIfnoSetting INSTANCE = new KapuaApkIfnoSetting();

    /**
     * Initialize the {@link AbstractKapuaSetting} with the {@link KapuaAccountSettingKeys#ACCOUNT_KEY} value.
     * 
     */
    private KapuaApkIfnoSetting() {
        super(ACCOUNT_CONFIG_RESOURCE);
    }

    /**
     * Gets a singleton instance of {@link KapuaAccountSetting}.
     * 
     * @return A singleton instance of KapuaAccountSetting.
     */
    public static KapuaApkIfnoSetting getInstance() {
        return INSTANCE;
    }
}
