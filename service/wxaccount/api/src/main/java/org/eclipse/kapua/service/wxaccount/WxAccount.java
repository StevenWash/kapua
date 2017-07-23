package org.eclipse.kapua.service.wxaccount;

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
@XmlRootElement(name = "wxAccount")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
		"wxAppid",
        "wxToken",
        "wxAeskey",
        "urlName",
        "wxAppsecret"
        }, factoryClass = WxAccountXmlRegistry.class, factoryMethod = "newWxAccount")
public interface WxAccount extends KapuaUpdatableEntity{

    public static final String TYPE = "wxAccount";

    public default String getType() {
        return TYPE;
    }

    /**
     * Get the appVersion's packagename
     * 
     * @return
     */
   @XmlElement(name = "wxAppid")
    public String getWxAppid();

    /**
     * Set the appVersion's packagename
     * 
     * @param packagename
     */
    public void setWxAppid(String  wxAppid);
    
    /**
     * Get the appVersion's code
     * 
     * @return          wx_appsecret
     */ 
  

    
    
    /**
     * Get the appVersion's version
     * 
     * @return
     */
    @XmlElement(name = "wxToken")
    public String getWxToken();

    /**
     * Set the appVersion's version
     * 
     * @param version
     */
    public void setWxToken(String wxToken);
    
    

    /**
     * Get the appVersion's url
     * 
     * @return
     */
    @XmlElement(name = "wxAeskey")
    public String getWxAeskey();

    /**
     * Set the appVersion's url
     * 
     * @param url
     */
    public void setWxAeskey(String wxAeskey);
    
    
    /**
     * Get the appVersion's size
     * 
     * @return
     */
    @XmlElement(name = "urlName")
    public String getUrlName();

    /**
     * Set the appVersion's size
     * 
     * @param size
     */
    public void setUrlName(String urlName);
    
    
    @XmlElement(name = "wxAppsecret")
    public String getWxAppsecret();

    /**
     * Set the appVersion's size
     * 
     * @param size
     */
    public void setWxAppsecret(String wxAppsecret);
    
    
   
}
