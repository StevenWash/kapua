package org.eclipse.kapua.service.replymessage;

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
@XmlRootElement(name = "replyMessage")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {       
		"types",
		"keywords",
        "content",
        "title",
        "description",
        "picurl",
        "url",
        "processrecoveryId"
        }, factoryClass = ReplyMessageXmlRegistry.class, factoryMethod = "newReplyMessage")
public interface ReplyMessage extends KapuaUpdatableEntity{

    public static final String TYPE = "replyMessage";

    public default String getType() {
        return TYPE;
    }

    /**
     * Get the ReplyMessage's types
     * 
     * @return
     */
   @XmlElement(name = "types")
    public String getTypes();

    /**
     * Set the ReplyMessage's types
     * 
     * @param types
     */
    public void setTypes(String  types);
    
    /**
     * Get the ReplyMessage's keywords
     * 
     * @return
     */
  
    @XmlElement(name = "keywords")
    public String getKeywords();

    /**
     * Set the ReplyMessage's keywords
     * 
     * @param keywords
     */
    public void setKeywords(String keywords);
    
    /**
     * Get the ReplyMessage's content
     * 
     * @return
     */
    @XmlElement(name = "content")
    public String getContent();

    /**
     * Set the ReplyMessage's content
     * 
     * @param content
     */
    public void setContent(String content);
    
    

    /**
     * Get the ReplyMessage's title
     * 
     * @return
     */
    @XmlElement(name = "title")
    public String getTitle();

    /**
     * Set the ReplyMessage's title
     * 
     * @param title
     */
    public void setTitle(String title);
    
    
    /**
     * Get the ReplyMessage's description
     * 
     * @return
     */
    @XmlElement(name = "description")
    public String getDescription();

    /**
     * Set the ReplyMessage's description
     * 
     * @param description
     */
    public void setDescription(String description);
    
    
    /**
     * Get the ReplyMessage's picurl
     * 
     * @return
     */
    @XmlElement(name = "picurl")
    public String getPicurl();

    /**
     * Set the ReplyMessage's picurl
     * 
     * @param picurl
     */
    public void setPicurl(String picurl);
    
    
    /**
     * Get the ReplyMessage's processrecoveryId
     * 
     * @return
     */

    @XmlElement(name = "processrecoveryId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    public KapuaId getProcessrecoveryId();
    
    
    
    
    public void setProcessrecoveryId(KapuaId getProcessrecoveryId);
    
    
    
    @XmlElement(name = "url")
    public String getUrl();

    /**
     * Set the ReplyMessage's url
     * 
     * @param url
     */
    public void setUrl(String url);

    

}
