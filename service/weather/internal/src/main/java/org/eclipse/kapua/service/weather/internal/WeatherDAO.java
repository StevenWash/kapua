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
package org.eclipse.kapua.service.weather.internal;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherCreator;
import org.eclipse.kapua.service.weather.WeatherListResult;
import org.eclipse.kapua.service.weather.WeatherQuery;
import org.eclipse.kapua.service.weather.internal.WeatherImpl;
import org.eclipse.kapua.service.weather.internal.WeatherListResultImpl;

/**
 * {@link weather} DAO
 * 
 * @since 1.0.0
 */
public class WeatherDAO extends ServiceDAO {

    /**
     * Creates and returns new {@link weather}
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param creator
     *            The {@link weatherCreator} object from which create the new {@link weather}.
     * @return The newly created {@link weather}.
     * @throws KapuaException
     * @since 1.0.0
     */
    public static Weather create(EntityManager em, WeatherCreator creator)
            throws KapuaException {
        Weather weather = new WeatherImpl();

        weather.setProvince(creator.getProvince());
        weather.setCity(creator.getCity());
        weather.setArea(creator.getArea());
        weather.setCity_code(creator.getCity_code());


        return ServiceDAO.create(em, weather);
    }

    /**
     * Finds the {@link weather} by {@link weather} identifier
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param weatherId
     *            The {@link weather} id to search.
     * @return The found {@link weather} or {@code null} if not found.
     * @since 1.0.0
     */
    public static Weather find(EntityManager em, KapuaId weatherId) {
        return em.find(WeatherImpl.class, weatherId);
    }

    /**
     * Deletes the {@link weather} by {@link weather} identifier
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param weatherId
     *            The {@link weather} id to delete.
     * @throws KapuaEntityNotFoundException
     *             If {@link weather} is not found.
     * @since 1.0.0
     */
    public static void delete(EntityManager em, KapuaId weatherId) throws KapuaEntityNotFoundException {
        ServiceDAO.delete(em, WeatherImpl.class, weatherId);
    }

    /**
     * Returns the {@link weather} list matching the provided query.
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param weatherQuery
     *            The {@link weatherQuery} used to filter results.
     * @return The list of {@link weather}s that matches the given query.
     * @throws KapuaException
     * @since 1.0.0
     */
    public static WeatherListResult query(EntityManager em, KapuaQuery<Weather> weatherQuery)
            throws KapuaException {
        weatherQuery.setScopeId(null);
        return ServiceDAO.query(em, Weather.class, WeatherImpl.class, new WeatherListResultImpl(), weatherQuery);
    }

    /**
     * Return the {@link weather} count matching the provided query
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param weatherQuery
     *            The {@link weatherQuery} used to filter results.
     * @return
     * @throws KapuaException
     * @since 1.0.0
     */
    public static long count(EntityManager em, KapuaQuery<Weather> weatherQuery)
            throws KapuaException {
        weatherQuery.setScopeId(null);
        return ServiceDAO.count(em, Weather.class, WeatherImpl.class, weatherQuery);
    }
}
