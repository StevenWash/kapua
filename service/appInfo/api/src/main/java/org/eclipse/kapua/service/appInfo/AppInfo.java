package org.eclipse.kapua.service.appInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaEntity;
import org.eclipse.kapua.model.KapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;

/**
 * User account entity.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "appInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
		"packagename",
		"comment",
        "image",
        "types",
        "name"
        
        })
public interface AppInfo extends KapuaUpdatableEntity{

    public static final String TYPE = "appInfo";

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
    
    
    
  
    @XmlElement(name = "image")
    public String getImage();

    /**
     * Set the weather's province
     * 
     * @param province
     */
    public void setImage(String image);
    
 
    
    
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
    

    
    
  
}
