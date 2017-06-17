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
package org.eclipse.kapua.service.weather;

import org.eclipse.kapua.model.KapuaEntityFactory;
import org.eclipse.kapua.model.id.KapuaId;

/**
 * Account factory service definition.
 * 
 * @since 1.0
 * 
 */
public interface WeatherFactory extends KapuaEntityFactory<Weather, WeatherCreator, WeatherQuery, WeatherListResult> {

    /**
     * Creates a new {@link WeatherCreator} for the specified name
     * 
     * @param province
     * @param city
     * @param area
     * @param city_code
     * @return
     */
    public WeatherCreator newCreator(String province,String city,String area,String city_code);

   

}
