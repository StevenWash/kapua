package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaUpdatableEntity;



/**
 * User appVersion entity.
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
        "url",
        "size",
        "md5",
        "types",
        "revision",
        "forversion"
        }, factoryClass = AppVersionXmlRegistry.class, factoryMethod = "newAppVersion")
public interface AppVersion extends KapuaUpdatableEntity{

    public static final String TYPE = "appVersion";

    public default String getType() {
        return TYPE;
    }

    /**
     * Get the appVersion's packagename
     * 
     * @return
     */
   @XmlElement(name = "packagename")
    public String getPackagename();

    /**
     * Set the appVersion's packagename
     * 
     * @param packagename
     */
    public void setPackagename(String  packagename);
    
    /**
     * Get the appVersion's code
     * 
     * @return
     */
  
    @XmlElement(name = "code")
    public String getCode();

    /**
     * Set the appVersion's code
     * 
     * @param code
     */
    public void setCode(String code);
    
    /**
     * Get the appVersion's version
     * 
     * @return
     */
    @XmlElement(name = "version")
    public String getVersion();

    /**
     * Set the appVersion's version
     * 
     * @param version
     */
    public void setVersion(String version);
    
    

    /**
     * Get the appVersion's url
     * 
     * @return
     */
    @XmlElement(name = "url")
    public String getUrl();

    /**
     * Set the appVersion's url
     * 
     * @param url
     */
    public void setUrl(String url);
    
    
    /**
     * Get the appVersion's size
     * 
     * @return
     */
    @XmlElement(name = "size")
    public Integer getSize();

    /**
     * Set the appVersion's size
     * 
     * @param size
     */
    public void setSize(Integer size);
    
    
    /**
     * Get the appVersion's md5
     * 
     * @return
     */
    @XmlElement(name = "md5")
    public String getMd5();

    /**
     * Set the appVersion's md5
     * 
     * @param md5
     */
    public void setMd5(String md5);
    
    
    /**
     * Get the appVersion's types
     * 
     * @return
     */
    @XmlElement(name = "types")
    public String getTypes();

    /**
     * Set the  appVersion's types
     * 
     * @param types
     */
    public void setTypes(String types);
    
    /**
     * Get the appVersion's revision
     * 
     * @return
     */
    @XmlElement(name = "revision")
    public String getRevision();

    /**
     * Set the appVersion's revision
     * 
     * @param revision
     */
    public void setRevision(String revision);
    
    
    
    /**
     * Get the appVersion's forversion.
     * 
     * @return
     */
    @XmlElement(name = "forversion")
    public String getForversion();

    /**
     * Set the appVersion's forversion.
     * 
     * @param forversion
     */
    public void setForversion(String forversion);
}
