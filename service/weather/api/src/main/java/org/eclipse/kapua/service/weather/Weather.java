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
     * Get the weather's province
     * 
     * @return
     */
  
    @XmlElement(name = "province")
    public String getProvince();

    /**
     * Set the weather's province
     * 
     * @param province
     */
    public void setProvince(String province);
    
    /**
     * Get the weather's city
     * 
     * @return
     */
    @XmlElement(name = "city")
    public String getCity();

    /**
     * Set the weather's city
     * 
     * @param city
     */
    public void setCity(String city);
    
    
    /**
     * Get the weather's area
     * 
     * @return
     */
    @XmlElement(name = "area")
    public String getArea();

    /**
     * Set the weather's area
     * 
     * @param area
     */
    public void setArea(String area);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "city_code")
    public String getCity_code();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setCity_code(String city_code);
}
