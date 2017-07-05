package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.kapua.model.KapuaUpdatableEntity;


/**
 * User account entity.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "appVersion")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
		"packagename",
		"code",
        "version",
        "name",
        "comment",
        "url",
        "size",
        "md5",
        "types",
        "revision",
        "forversion"
        
        })
public interface AppVersion extends KapuaUpdatableEntity{

    public static final String TYPE = "appVersion";

    public default String getType() {
        return TYPE;
    }

    
    @XmlElement(name = "packagename")
    public String getPackagename();

    /**
     * Set the weather's province
     * 
     * @param province
     */
    public void setPackagename(String  packagename);
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
    @XmlElement(name = "version")
    public String getVersion();

    /**
     * Set the weather's city
     * 
     * @param city
     */
    public void setVersion(String version);
    
    
    /**
     * Get the weather's area
     * 
     * @return
     */
    @XmlElement(name = "name")
    public String getName();

    /**
     * Set the weather's area
     * 
     * @param area
     */
    public void setName(String name);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "comment")
    public String getComment();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setComment(String comment);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "url")
    public String getUrl();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setUrl(String url);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "size")
    public Integer getSize();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setSize(Integer size);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "md5")
    public String getMd5();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setMd5(String md5);
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "types")
    public String getTypes();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setTypes(String types);
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "revision")
    public String getRevision();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setRevision(String revision);
    
    
    
    /**
     * Get the weather's city_code
     * 
     * @return
     */
    @XmlElement(name = "forversion")
    public String getForversion();

    /**
     * Set the weather's city_code.
     * 
     * @param city_code
     */
    public void setForversion(String forversion);
}
