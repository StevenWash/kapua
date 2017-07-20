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
package org.eclipse.kapua.service.appinfo.internal;

import org.eclipse.kapua.commons.model.query.predicate.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoQuery;

/**
 * User roles factory service implementation.
 *
 * @since 1.0
 */
public class AppInfoQueryImpl extends AbstractKapuaQuery<AppInfo> implements  AppInfoQuery {

    /**
     * Constructor
     */
    private AppInfoQueryImpl() {
        super();
    }

    /**
     * Constructor
     *
     * @param scopeId
     */
    public AppInfoQueryImpl(KapuaId scopeId) {
        this();
        setScopeId(scopeId);
    }

}
