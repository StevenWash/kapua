package org.eclipse.kapua.service.wxaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaNamedEntityCreator;


@XmlRootElement(name = "wxAccountCreator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { 
		"wxAppid",
        "wxToken",
        "wxAeskey",
        "urlName",
        "wxAppsecret"
         }, factoryClass = WxAccountXmlRegistry.class, factoryMethod = "newWxAccountCreator")
public interface  WxAccountCreator  extends KapuaNamedEntityCreator<WxAccount>{
	
	
	/**
     * Get the packagename
     * 
     * @return
     */
    @XmlElement(name = "wxAppid")
    public String getWxAppid();

    /**
     * Set the packagename
     * 
     * @param packagename
     */
    public void setWxAppid(String wx_appid);
    
    
    
    /**
     * Get the code
     * 
     * @return
     */
 

    
    
    
    
    /**
     * Get the version
     * 
     * @return
     */
    @XmlElement(name = "wxToken")
    public String getWxToken();

    /**
     * Set the version
     * 
     * @param version
     */
    public void setWxToken(String wx_token);
    
    
    
    /**
     * Get the url
     * 
     * @return
     */
    @XmlElement(name = "wxAeskey")
    public String getWxAeskey();

    /**
     * Set the url
     * 
     * @param url
     */
    public void setWxAeskey(String wx_aeskey);
    
    
    /**
     * Get the size
     * 
     * @return
     */
    @XmlElement(name = "urlName")
    public String getUrlName();

    /**
     * Set the size
     * 
     * @param size
     */
    public void setUrlName(String urlName);
    
    
    @XmlElement(name = "wxAppsecret")
    public String getWxAppsecret();

    /**
     * Set the size
     * 
     * @param size
     */
    public void setWxAppsecret(String wxAppsecret);
    
 
	      
}
