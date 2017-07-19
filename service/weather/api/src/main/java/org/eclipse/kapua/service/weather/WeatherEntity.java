package org.eclipse.kapua.service.weather;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.kapua.KapuaSerializable;

public interface WeatherEntity extends KapuaSerializable{
	
	@XmlTransient
    public String getType();

}
