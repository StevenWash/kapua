package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.kapua.model.KapuaEntityCreator;


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
     * Get the packagename
     * 
     * @return
     */
    @XmlElement(name = "packagename")
    public String getPackagename();

    /**
     * Set the packagename
     * 
     * @param packagename
     */
    public void setPackagename(String packagename);
    
    
    
    /**
     * Get the code
     * 
     * @return
     */
    @XmlElement(name = "code")
    public String getCode();

    /**
     * Set the code
     * 
     * @param code
     */
    public void setCode(String code);
    
    
    
    
    /**
     * Get the version
     * 
     * @return
     */
    @XmlElement(name = "version")
    public String getVersion();

    /**
     * Set the version
     * 
     * @param version
     */
    public void setVersion(String version);
    
    
    
    /**
     * Get the url
     * 
     * @return
     */
    @XmlElement(name = "url")
    public String getUrl();

    /**
     * Set the url
     * 
     * @param url
     */
    public void setUrl(String url);
    
    
    /**
     * Get the size
     * 
     * @return
     */
    @XmlElement(name = "size")
    public Integer getSize();

    /**
     * Set the size
     * 
     * @param size
     */
    public void setSize(Integer size);
    
    
    /**
     * Get the md5
     * 
     * @return
     */
    @XmlElement(name = "md5")
    public String getMd5();

    /**
     * Set the md5
     * 
     * @param md5
     */
    public void setMd5(String md5);
    
    
    /**
     * Get the types
     * 
     * @return
     */
    @XmlElement(name = "types")
    public String getTypes();

    /**
     * Set the types
     * 
     * @param types
     */
    public void setTypes(String types);
    
    
    /**
     * Get the revision
     * 
     * @return
     */
    @XmlElement(name = "revision")
    public String getRevision();

    /**
     * Set the revision
     * 
     * @param revision
     */
    public void setRevision(String revision);
    
    
    /**
     * Get the forversion
     * 
     * @return
     */
    @XmlElement(name = "forversion")
    public String getForversion();

    /**
     * Set the forversion
     * 
     * @param forversion
     */
    public void setForversion(String forversion);
    
    
   
	      
}
