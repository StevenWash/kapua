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
package org.eclipse.kapua.service.weather;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.KapuaService;



/**
 * WeatherService exposes APIs to manage Weather objects.<br>
 * It includes APIs to create, update, find, list and delete Weathers.<br>
 * Instances of the WeatherService can be acquired through the ServiceLocator object.
 * 
 * @since 1.0
 * 
 */
public interface WeatherCodeService extends KapuaService{
        

	public WeatherCode findByCname(String cname)
            throws KapuaException;

   
 
}
