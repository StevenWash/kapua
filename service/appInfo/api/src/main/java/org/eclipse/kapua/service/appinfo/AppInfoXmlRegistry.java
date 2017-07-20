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
package org.eclipse.kapua.service.appinfo;

import javax.xml.bind.annotation.XmlRegistry;

import org.eclipse.kapua.locator.KapuaLocator;

/**
 * Account xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class AppInfoXmlRegistry {

    private final KapuaLocator locator = KapuaLocator.getInstance();
    private final AppInfoFactory factory = locator.getFactory(AppInfoFactory.class);

    /**
     * Creates a new account instance
     * 
     * @return
     */
    public AppInfo newAppInfo() {
    	System.out.println("factory::>>>>>>>>>>>"+factory);
        return factory.newEntity(null);
    }

    /**
     * Creates a new organization instance
     * 
     * @return
     */
 
    /**
     * Creates a new account creator instance
     * 
     * @return
     */
    public AppInfoCreator newAppInfoCreator() {
        return factory.newCreator(null);
    }

    /**
     * Creates a new account list result instance
     * 
     * @return
     */
    public AppInfoListResult newAppInfoListResult() {
        return factory.newListResult();
    }

    public AppInfoQuery newQuery() {
        return factory.newQuery(null);
    }
}
