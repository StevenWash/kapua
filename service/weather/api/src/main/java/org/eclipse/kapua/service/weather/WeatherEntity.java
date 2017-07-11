package org.eclipse.kapua.service.weather;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

public interface WeatherEntity extends Serializable{
	
	@XmlTransient
    public String getType();

}
