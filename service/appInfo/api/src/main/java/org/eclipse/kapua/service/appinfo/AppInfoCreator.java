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
package org.eclipse.kapua.service.appinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaNamedEntityCreator;

/**
 * AccountCreator encapsulates all the information needed to create a new Account in the system.<br>
 * The data provided will be used to seed the new Account and its related information such as the associated organization and users.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "appInfoCreator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "packagename",
        "name",
        "comment",
        "types",
        "image"
        }, factoryClass = AppInfoXmlRegistry.class, factoryMethod = "newAppInfoCreator")
public interface AppInfoCreator extends KapuaNamedEntityCreator<AppInfo> {

    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "packagename")
    public String getPackagename();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setPackagename(String packagename);

    /**
     * Get organization referent name
     * 
     * @return
     */

    /**
     * Get the organization email
     * 
     * @return
     */
    @XmlElement(name = "comment")
    public String getComment();

    /**
     * Set the organization email
     * 
     * @param organizationEmail
     */
    public void setComment(String comment);

    /**
     * Get the organization phone number
     * 
     * @return
     */
    @XmlElement(name = "types")
    public String getTypes();

    /**
     * Set the organization phone number
     * 
     * @param organizationPhoneNumber
     */
    public void setTypes(String types);

    /**
     * Get organization address (first line)
     * 
     * @return
     */
    @XmlElement(name = "image")
    public String getImage();

    /**
     * Set organization address (first line)
     * 
     * @param organizationAddressLine1
     */
    public void setImage(String image);

   
}
