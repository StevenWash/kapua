package org.eclipse.kapua.service.processrecovery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.kapua.model.KapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdAdapter;



/**
 * User appVersion entity.
 * 
 * @since 1.0
 *
 */
@XmlRootElement(name = "processrecovery")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {       
		"nextId",
		"parentId",
        "dataTitle",
        "dataContent"
        }, factoryClass = ProcessRecoveryXmlRegistry.class, factoryMethod = "newProcessRecovery")
public interface ProcessRecovery extends KapuaUpdatableEntity{

    public static final String TYPE = "processrecovery";

    public default String getType() {
        return TYPE;
    }

    /**
     * Get the ReplyMessage's types
     * 
     * @return
     */
   @XmlElement(name = "nextId")
    public Integer getNextId();

    /**
     * Set the ReplyMessage's types
     * 
     * @param types
     */
    public void setNextId(Integer  nextId);
    
    /**
     * Get the ReplyMessage's keywords
     * 
     * @return
     */
  
    @XmlElement(name = "parentId")
    public Integer getParentId();

    /**
     * Set the ReplyMessage's keywords
     * 
     * @param keywords
     */
    public void setParentId(Integer parentId);
    

    
    

    /**
     * Get the ReplyMessage's title
     * 
     * @return
     */
    @XmlElement(name = "dataTitle")
    public String getDataTitle();

    /**
     * Set the ReplyMessage's title
     * 
     * @param title
     */
    public void setDataTitle(String dataTitle);
    
    
    /**
     * Get the ReplyMessage's description
     * 
     * @return
     */
    @XmlElement(name = "dataContent")
    public String getDataContent();

    /**
     * Set the ReplyMessage's description
     * 
     * @param description
     */
    public void setDataContent(String dataContent);
    

}
