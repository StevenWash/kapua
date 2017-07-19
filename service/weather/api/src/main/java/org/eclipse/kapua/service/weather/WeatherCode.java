package org.eclipse.kapua.service.weather;

import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.KapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdAdapter;

/**
 * User account entity.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "weatherCode")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id","code",
        "explain","cname","ename","dayImage","nightImage" })
public interface WeatherCode  extends WeatherEntity {
	//extends WeatherEntity
    public static final String TYPE = "weatherCode";

    default public String getType() {
        return TYPE;
    }

    
   
    
    @XmlElement(name = "id")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    public KapuaId getId();

    /**
     * Set the weather's province
     * 
     * @param province
     */
    public void setId(KapuaId id);
    /**
     * Get the weather's province
     * 
     * @return
     */
  
    @XmlElement(name = "code")
    public String getCode();

    /**
     * Set the weather's province
     * 
     * @param province
     */
    public void setCode(String code);
    
    /**
     * Get the weather's city
     * 
     * @return
     */
    @XmlElement(name = "explain")
    public String getExplain();

    /**
     * Set the weather's city
     * 
     * @param city
     */
    public void setExplain(String explain);
    
    
    /**
     * Get the weather's area
     * 
     * @return
     */
    @XmlElement(name = "cname")
    public String getCname();

    /**
     * Set the weather's area
     * 
     * @param area
     */
    public void setCname(String cname);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "ename")
    public String getEname();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setEname(String ename);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "dayImage")
    public String getDayImage();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setDayImage(String dayImage);
    
    
    
    /**
     * Get the weatherCode's nightImage
     * 
     * @return
     */
    @XmlElement(name = "nightImage")
    public String getNightImage();

    /**
     * Set the weatherCode's nightImage.
     * 
     * @param nightImage
     */
    public void setNightImage(String nightImage);
    
    

}
