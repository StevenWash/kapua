package org.eclipse.kapua.service.appinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaNamedEntity;


/**
 * User AppInfo entity.
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
        "types"
         }, factoryClass = AppInfoXmlRegistry.class, factoryMethod = "newAppInfo")
public interface AppInfo extends KapuaNamedEntity{

    public static final String TYPE = "appInfo";

    public default String getType() {
        return TYPE;
    }
  
    /**
     * Get the AppInfo's  packagename
     * 
     * @return
     */
    
    @XmlElement(name = "packagename")
    public String getPackagename();

    /**
     * Set the AppInfo's  packagename
     * 
     * @param packagename
     */
    public void setPackagename(String  packagename);
   
    
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
