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
package org.eclipse.kapua.service.appinfo.internal;

import org.eclipse.kapua.commons.model.AbstractKapuaNamedEntityCreator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appinfo.AppInfo;
import org.eclipse.kapua.service.appinfo.AppInfoCreator;

/**
 * Account creator service implementation.
 * 
 * @since 1.0
 * 
 */
public class AppInfoCreatorImpl extends AbstractKapuaNamedEntityCreator<AppInfo> implements AppInfoCreator {

    

    

    /**
	 * 
	 */
	private static final long serialVersionUID = -3035892817938318227L;
	
	
	 private String packagename;
	 
	 private String comment;
	 
	 private String types;
	 
	 private String url;
	 
	 private String image;
	 
	 
	   /**
	     * Constructor
	     * 
	     * @param scopeId
	     * @param name
	     *            appInfo name
	     */
	    public AppInfoCreatorImpl(KapuaId scopeId, String name) {
	        super(scopeId, name);
	    }

	/**
     * Constructor
     * 
     * @param scopeId
     * 
     *           
     */
    public AppInfoCreatorImpl(KapuaId scopeId) {
        super(scopeId);
    }

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

    /**
     * Get the account name
     * 
     * @return
     */

     
}
