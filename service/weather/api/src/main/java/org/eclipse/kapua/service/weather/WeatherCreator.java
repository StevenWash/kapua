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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaEntityCreator;
import org.eclipse.kapua.service.KapuaService;
/**
 * AccountCreator encapsulates all the information needed to create a new Account in the system.<br>
 * The data provided will be used to seed the new Account and its related information such as the associated organization and users.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "weatherCreator")
@XmlAccessorType(XmlAccessType.PROPERTY)
public interface WeatherCreator extends KapuaEntityCreator<Weather> {

    /**
     * Sets the {@link Domain} name.
     * 
     * @param name
     *            The {@link Domain} name.
     * 
     * @since 1.0.0
     */
    public void setProvince(String province);

    /**
     * Gets the {@link Domain} name.
     * 
     * @return The {@link Domain} name.
     * @since 1.0.0
     */
    @XmlElement(name = "province")
    public String getProvince();

    /**
     * Sets the {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @param serviceName
     *            The {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @since 1.0.0
     */
    public void setArea(String area);

    /**
     * Gets the {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @return The {@link KapuaService} name that uses the {@link Domain}.
     * @since 1.0.0
     */
    @XmlElement(name = "area")
    public String getArea();
    
    
    /**
     * Sets the {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @param serviceName
     *            The {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @since 1.0.0
     */
    public void setCity(String city);

    /**
     * Gets the {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @return The {@link KapuaService} name that uses the {@link Domain}.
     * @since 1.0.0
     */
    @XmlElement(name = "city")
    public String getCity();
    
    
    /**
     * Sets the {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @param serviceName
     *            The {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @since 1.0.0
     */
    public void setCity_code(String city_code);

    /**
     * Gets the {@link KapuaService} name that uses the {@link Domain}.
     * 
     * @return The {@link KapuaService} name that uses the {@link Domain}.
     * @since 1.0.0
     */
    @XmlElement(name = "city_code")
    public String getCity_code();

    


    
}
