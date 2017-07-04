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
package org.eclipse.kapua.service.appInfo.internal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


import org.eclipse.kapua.service.appInfo.AppInfo;
import org.eclipse.kapua.commons.model.AbstractKapuaEntity;
import org.eclipse.kapua.commons.model.AbstractKapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;


/**
 * Weather entity implementation.
 *
 * @since 1.0
 */
@Entity(name = "AppInfo")
@NamedQueries({
	@NamedQuery(name = "AppInfo.queryByPackagename", query = "select ak from AppInfo ak where ak.packagename=?1")
//	@NamedQuery(name = "ApkInfo.queryByDistinct", query = "select ak from AppInfo ak where ak.packagename=?1 and ak.name=?2")
})
@Table(name = "app_info")
public class AppInfoImpl extends AbstractKapuaUpdatableEntity implements AppInfo {

    private static final long serialVersionUID = 8530992430658117928L;
    
 
 
    @Basic
    @Column(name = "packagename", nullable = false)
    private String packagename;
    
    

   
    
 
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    
    
    
    @Basic
    @Column(name = "comment", nullable = false)
    private String comment;
    
    
    
  
    
    
    @Basic
    @Column(name = "types", nullable = false)
    private String types;
    
  
    
    @Basic
    @Column(name = "image", nullable = false)
    private String image;
    
    
 

    /**
     * Constructor
     */
    public AppInfoImpl() {
       
    }
    


	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}



	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}








	

	

 


}
