package org.eclipse.kapua.service.processrecovery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.KapuaEntityCreator;


@XmlRootElement(name = "processRecoveryCreator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "nextId",
        "parentId",
        "dataContent",
        "dataTitle"
         }, factoryClass = ProcessRecoveryXmlRegistry.class, factoryMethod = "newProcessRecoveryCreator")
public interface  ProcessRecoveryCreator  extends KapuaEntityCreator<ProcessRecovery>{
	
	
	/**
     * Get the types
     * 
     * @return
     */
    @XmlElement(name = "nextId")
    public Integer getNextId();

    /**
     * Set the types
     * 
     * @param types
     */
    public void setNextId(Integer nextId);
    
    
    
    /**
     * Get the keywords
     * 
     * @return
     */
    @XmlElement(name = "parentId")
    public Integer getParentId();

    /**
     * Set the keywords
     * 
     * @param keywords
     */
    public void setParentId(Integer parentId);
    
    
    
    
    /**
     * Get the content
     * 
     * @return
     */
    @XmlElement(name = "dataContent")
    public String getDataContent();

    /**
     * Set the content
     * 
     * @param content
     */
    public void setDataContent(String dateContent);
    
    
    
    /**
     * Get the url
     * 
     * @return
     */
    @XmlElement(name = "dataTitle")
    public String getDataTitle();

    /**
     * Set the url
     * 
     * @param url
     */
    public void setDataTitle(String dataTitle);
    
    
    
}
