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

import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.model.id.KapuaId;


/**
 * Weather entity implementation.
 *
 * @since 1.0
 */
@Entity(name = "Weather")
@NamedQueries({
	@NamedQuery(name = "Weather.queryProvince", query = "select distinct  w.province from Weather w"),
	@NamedQuery(name = "Weather.queryCityByProvince", query = "select distinct w.city from Weather w where w.province=?1"),
	@NamedQuery(name = "Weather.queryAreaByCity", query = "select distinct w.area from Weather w where w.city=?1")
})
@Table(name = "city_weather")
public class WeatherImpl  implements Weather {

   
    
   /**
	 * 
	 */
	private static final long serialVersionUID = 6373123852831232686L;


/* @Basic
    @EmbeddedId
    @Column(name = "id", nullable = false)*/
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, updatable = false))
    })
    private KapuaId id;

  
    @Basic
    @Column(name = "city_code", nullable = false)
    private String city_code;
    
    

    @Basic
    @Column(name = "province", nullable = false)
    private String province;
    
    @Basic
    @Column(name = "city", nullable = false)
    private String city;
    
    @Basic
    @Column(name = "area", nullable = false)
    private String area;

    /**
     * Constructor
     */
    public WeatherImpl() {
       
    }
    
    /**
     * Constructor
     *
     * @param id,province,city,,area,city_code
     */
    public WeatherImpl(KapuaId id,String province,String city,String area,String city_code) {
    	
    	 this.id=id;
    	 this.province=province;
    	 this.city=city;
    	 this.area=area;
    	 this.city_code=city_code;
       
       
    }

 


    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public String getArea() {
        return area;
    }

    @Override
    public void setArea(String area) {
        this.area = area;
    }
    
    @Override
    public String getCity_code() {
        return city_code;
    }

    @Override
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

//    @Id
//	@GeneratedValue
//	@EmbeddedId
	public KapuaId getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(KapuaId id) {
		// TODO Auto-generated method stub
		this.id=id;
		
	}
}
