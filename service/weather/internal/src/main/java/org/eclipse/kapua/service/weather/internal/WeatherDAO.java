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

import org.eclipse.kapua.service.weather.internal.WeatherImpl;


/**
 * {@link weather} DAO
 * 
 * @since 1.0.0
 */
public class WeatherDAO {



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
  /*  public static Weather find(EntityManager em,KapuaId id) {
        return em.find(WeatherImpl.class, id);
    }*/

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
 
}
