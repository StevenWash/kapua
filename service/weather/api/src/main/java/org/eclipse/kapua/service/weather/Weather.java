package org.eclipse.kapua.service.weather;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaEntity;

/**
 * User account entity.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "weather")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"city_code",
        "province","city","area" })
public interface Weather extends KapuaEntity {

    public static final String TYPE = "weather";

    default public String getType() {
        return TYPE;
    }

    /**
     * Get the weather's organization
     * 
     * @return
     */
  
    @XmlElement(name = "province")
    public String getProvince();

    /**
     * Set the parent account path.
     * 
     * @param parentAccountPath
     */
    public void setProvince(String province);
    
    
    @XmlElement(name = "city")
    public String getCity();

    /**
     * Set the parent account path.
     * 
     * @param parentAccountPath
     */
    public void setCity(String city);
    
    @XmlElement(name = "area")
    public String getArea();

    /**
     * Set the parent account path.
     * 
     * @param parentAccountPath
     */
    public void setArea(String area);
    
    @XmlElement(name = "city_code")
    public String getCity_code();

    /**
     * Set the parent account path.
     * 
     * @param parentAccountPath
     */
    public void setCity_code(String city_code);
}
