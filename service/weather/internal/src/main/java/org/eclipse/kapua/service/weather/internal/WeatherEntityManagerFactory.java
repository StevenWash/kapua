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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.kapua.commons.jpa.AbstractEntityManagerFactory;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.jpa.EntityManagerFactory;
import org.eclipse.kapua.KapuaException;

/**
 * Entity manager factory for the account module.
 * 
 * @since 1.0
 *
 */
public class WeatherEntityManagerFactory extends AbstractEntityManagerFactory{

    private static final String PERSISTENCE_UNIT_NAME ="kapua-weather";
    private static final String DATASOURCE_NAME = "kapua-dbpool";
    private static final Map<String, String> UNIQUE_CONTRAINTS = new HashMap<>();

    private static WeatherEntityManagerFactory instance = new WeatherEntityManagerFactory();

    /**
     * Constructs a new entity manager factory and configure it to use the account persistence unit.
     */
    private WeatherEntityManagerFactory() {
        super(PERSISTENCE_UNIT_NAME,
                DATASOURCE_NAME,
                UNIQUE_CONTRAINTS);
    }

    /**
     * Return the {@link EntityManager} singleton instance
     * 
     * @return
     */
    public static WeatherEntityManagerFactory getInstance() {
        return instance;
    }
    
    /**
     * Return the {@link EntityManager} singleton instance
     * 
     * @return
     * @throws KapuaException
     */
    public static EntityManager getEntityManager()
            throws KapuaException {
        return instance.createEntityManager();
    }
}
