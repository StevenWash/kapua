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
package org.eclipse.kapua.service.apkInfo.internal;

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

import org.eclipse.kapua.service.apkInfo.ApkInfo;

import org.eclipse.kapua.commons.model.AbstractKapuaEntity;
import org.eclipse.kapua.commons.model.AbstractKapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;


/**
 * Weather entity implementation.
 *
 * @since 1.0
 */
@Entity(name = "ApkInfo")
@NamedQueries({
	@NamedQuery(name = "ApkInfo.queryByPackagename", query = "select ak from ApkInfo ak where ak.packagename=?1"),
	@NamedQuery(name = "ApkInfo.queryByDistinct", query = "select ak from ApkInfo ak where ak.packagename=?1 and ak.forversion=?2")
})
@Table(name = "api_appinfo")
public class ApkInfoImpl extends AbstractKapuaUpdatableEntity implements ApkInfo {

    private static final long serialVersionUID = 8530992430658117928L;
    
 
 
    @Basic
    @Column(name = "packagename", nullable = false)
    private String packagename;
    
    

    @Basic
    @Column(name = "code", nullable = false)
    private String code;
    
    @Basic
    @Column(name = "version", nullable = false)
    private String version;
    
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    
    
    
    @Basic
    @Column(name = "comment", nullable = false)
    private String comment;
    
    
    
    @Basic
    @Column(name = "url", nullable = false)
    private String url;
    
    
    @Basic
    @Column(name = "size", nullable = false)
    private Integer size;
    
    
    @Basic
    @Column(name = "md5", nullable = false)
    private String md5;
    
    
    @Basic
    @Column(name = "types", nullable = false)
    private String types;
    
    
    @Basic
    @Column(name = "revision", nullable = false)
    private String revision;
    
    
    @Basic
    @Column(name = "forversion", nullable = false)
    private String forversion;

    /**
     * Constructor
     */
    public ApkInfoImpl() {
       
    }
    
    /**
     * Constructor
     *
     * @param id,province,city,,area,city_code
     */
    public ApkInfoImpl(String packagename,String code,String version,String name,String comment,String url,Integer size,String md5,String types,String revision,String forversion) {
    	
    	 this.packagename=packagename;
    	 this.code=code;
    	 this.version=version;
    	 this.name=name;
    	 this.comment=comment;
    	 this.url=url;
    	 this.size=size;
    	 this.md5=md5;
    	 this.types=types;
    	 this.revision=revision;
    	 this.forversion=forversion;
       
       
    }

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getForversion() {
		return forversion;
	}

	public void setForversion(String forversion) {
		this.forversion = forversion;
	}






	

	

 


}
