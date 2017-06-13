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
package org.eclipse.kapua.service.weather.internal;

import org.apache.commons.lang.NotImplementedException;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherCreator;
import org.eclipse.kapua.service.weather.WeatherFactory;
import org.eclipse.kapua.service.weather.WeatherListResult;
import org.eclipse.kapua.service.weather.WeatherQuery;


/**
 * Account service factory implementation.
 *
 * @since 1.0
 */
@KapuaProvider
public class WeatherFactoryImpl implements WeatherFactory {
    
    @Override
    public WeatherCreator newCreator(String province,String city,String area,String city_code) {
        return new WeatherCreatorImpl(province,city,area,city_code);
    }

   /* @Override
    public WeatherCreator newCreator(KapuaId scopeId, String name) {
    	WeatherCreator creator = newCreator(scopeId);
        creator.setName(name);
        return creator;
    }*/

    @Override
    public Weather newEntity(KapuaId scopeId) {
        return new WeatherImpl(scopeId);
    }

  

    @Override
    public WeatherQuery newQuery(KapuaId scopeId) {
        return new WeatherQueryImpl(scopeId);
    }

    @Override
    public WeatherListResult newListResult() {
        return new WeatherListResultImpl();
    }
    
    @Override
    public WeatherCreator newCreator(KapuaId scopeId) {
        throw new NotImplementedException();
    }

}
