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

import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoCreator;
import org.eclipse.kapua.service.appinfo.AppInfoFactory;
import org.eclipse.kapua.service.appinfo.AppInfoListResult;
import org.eclipse.kapua.service.appinfo.AppInfoQuery;

/**
 * AppInfo service factory implementation.
 *
 * @since 1.0
 */
@KapuaProvider
public class AppInfoFactoryImpl implements AppInfoFactory {
    
    @Override
    public AppInfoCreator newCreator(KapuaId scopeId) {
        return new AppInfoCreatorImpl(scopeId);
    }



    @Override
    public AppInfo newEntity(KapuaId scopeId) {
        return new AppInfoImpl();
    }

 

    @Override
    public AppInfoQuery newQuery(KapuaId scopeId) {
        return new AppInfoQueryImpl(scopeId);
    }

    @Override
    public AppInfoListResult newListResult() {
        return new AppInfoListResultImpl();
    }



	@Override
	public AppInfoCreator newCreator(KapuaId scopeId, String name) {
		// TODO Auto-generated method stub
		AppInfoCreator creator = newCreator(scopeId);
	    creator.setName(name);
	        
	        return creator;
	}


}
