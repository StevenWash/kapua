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

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.kapua.service.weather.WeatherCode;
import org.eclipse.kapua.model.id.KapuaId;


/**
 * WeatherCode entity implementation.
 *
 * @since 1.0
 */

@Entity(name = "WeatherCode")
@NamedQueries({
	@NamedQuery(name = "WeatherCode.queryByCname", query = "select  w from WeatherCode w  where w.cname=?1")
	})
@Table(name = "weather_code")
public class WeatherCodeImpl  implements WeatherCode {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2917638209954645471L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, updatable = false))
    })
    private KapuaId id;

  
    @Basic
    @Column(name = "code", nullable = false)
    private String code;
    
    @Basic
    @Column(name = "cname", nullable = false)
    private String cname;
    
    @Basic
    @Column(name = "ename", nullable = false)
    private String ename;
    
    @Basic
    @Column(name = "explain", nullable = false)
    private String explain;
    
    @Basic
    @Column(name = "dayImage", nullable = false)
    private String dayImage;
    
    @Basic
    @Column(name = "nightImage", nullable = false)
    private String nightImage;
    
    
    

    /**
     * Constructor
     */
    public WeatherCodeImpl() {
       
    }
    
    
    
    
    /**
     * Constructor
     *
     * @param id,province,city,,area,city_code
     * 
     * 
     */
	public WeatherCodeImpl(KapuaId id, String code, String cname, String ename,
			String explain, String dayImage, String nightImage) {
		super();
		this.id = id;
		this.code = code;
		this.cname = cname;
		this.ename = ename;
		this.explain = explain;
		this.dayImage = dayImage;
		this.nightImage = nightImage;
	}

    
    
    
    
    public KapuaId getId() {
		return id;
	}
    
   


	@Override
	public void setId(KapuaId id) {
		this.id=id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getDayImage() {
		return dayImage;
	}

	public void setDayImage(String dayImage) {
		this.dayImage = dayImage;
	}

	public String getNightImage() {
		return nightImage;
	}

	public void setNightImage(String nightImage) {
		this.nightImage = nightImage;
	}
	


	
}
