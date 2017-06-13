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


import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherCreator;
import org.eclipse.kapua.commons.model.AbstractKapuaEntityCreator;

/**
 * Account creator service implementation.
 * 
 * @since 1.0
 * 
 */
public class WeatherCreatorImpl extends AbstractKapuaEntityCreator<Weather> implements WeatherCreator {

    private static final long serialVersionUID = -2460883485294616032L;

    
    private String province;
    
    private String area;
    
    private String city;
    
    private String city_code;
    
    public  WeatherCreatorImpl(String province,String city,String area,String city_code){
    	super((KapuaId) null);
    	setProvince(province);
    	setCity(city);
    	setArea(area);
    	setCity_code(city_code);
    	
    }
    
    @Override
    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String getProvince() {
        return province;
    }
    
    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCity() {
        return city;
    }
    @Override
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String getArea() {
        return area;
    }
    @Override
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    @Override
    public String getCity_code() {
        return city_code;
    }
    
    
    


}
