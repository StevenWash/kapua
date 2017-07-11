package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;

import org.eclipse.kapua.model.KapuaEntityCreator;
import org.eclipse.kapua.model.KapuaNamedEntityCreator;

@XmlRootElement(name = "appVersionCreator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "packagename",
        "code",
        "version",
        "url",
        "size",
        "md5",
        "types",
        "revision",
        "forversion"
         }, factoryClass = AppVersionXmlRegistry.class, factoryMethod = "newAppVersionCreator")
public interface  AppVersionCreator  extends KapuaEntityCreator<AppVersion>{
	
	
	/**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "packagename")
    public String getPackagename();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setPackagename(String packagename);
    
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "code")
    public String getCode();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setCode(String code);
    
    
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "version")
    public String getVersion();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setVersion(String version);
    
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "url")
    public String getUrl();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setUrl(String url);
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "size")
    public Integer getSize();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setSize(Integer size);
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "md5")
    public String getMd5();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setMd5(String md5);
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "types")
    public String getTypes();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setTypes(String types);
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "revision")
    public String getRevision();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setRevision(String revision);
    
    
    /**
     * Get the organization name
     * 
     * @return
     */
    @XmlElement(name = "forversion")
    public String getForversion();

    /**
     * Set the organization name
     * 
     * @param organizationName
     */
    public void setForversion(String forversion);
    
    
   
	      
}
