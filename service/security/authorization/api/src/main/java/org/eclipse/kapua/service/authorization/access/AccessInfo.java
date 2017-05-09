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
package org.eclipse.kapua.service.authorization.access;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.kapua.model.KapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdAdapter;

/**
 * Access info entity definition.<br>
 * It contains all authorization accesses for a {@link org.eclipse.kapua.service.user.User}.<br>
 * It refers to the {@link org.eclipse.kapua.service.user.User} entity by the {@link AccessInfo#getUserId()} property.<br>
 * <br>
 * {@link AccessInfo} is unique by the {@link AccessInfo#getUserId()} property.
 * 
 * @since 1.0.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "userId" }, //
        factoryClass = AccessInfoXmlRegistry.class, //
        factoryMethod = "newAccessInfo")
public interface AccessInfo extends KapuaUpdatableEntity {

    public static final String TYPE = "accessInfo";

    public default String getType() {
        return TYPE;
    }

    /**
     * Sets the User id.
     * 
     * @param userId
     *            The User id.
     * 
     * @since 1.0.0
     */
    public void setUserId(KapuaId userId);

    /**
     * Gets the User id.
     * 
     * @return The User id.
     * 
     * @since 1.0.0
     */
    @XmlElement(name = "userId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    public KapuaId getUserId();
}
