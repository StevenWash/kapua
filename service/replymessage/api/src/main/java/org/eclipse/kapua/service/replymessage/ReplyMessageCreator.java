package org.eclipse.kapua.service.replymessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.kapua.model.KapuaEntityCreator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdAdapter;


@XmlRootElement(name = "appVersionCreator")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "types",
        "keywords",
        "content",
        "url",
        "title",
        "description",
        "picurl",
        "processrecoveryId"
         }, factoryClass = ReplyMessageXmlRegistry.class, factoryMethod = "newReplyMessageCreator")
public interface  ReplyMessageCreator  extends KapuaEntityCreator<ReplyMessage>{
	
	
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
     * Get the keywords
     * 
     * @return
     */
    @XmlElement(name = "keywords")
    public String getKeywords();

    /**
     * Set the keywords
     * 
     * @param keywords
     */
    public void setKeywords(String keywords);
    
    
    
    
    /**
     * Get the content
     * 
     * @return
     */
    @XmlElement(name = "content")
    public String getContent();

    /**
     * Set the content
     * 
     * @param content
     */
    public void setContent(String content);
    
    
    
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
     * Get the title
     * 
     * @return
     */
    @XmlElement(name = "title")
    public String getTitle();

    /**
     * Set the title
     * 
     * @param title
     */
    public void setTitle(String title);
    
    
    /**
     * Get the description
     * 
     * @return
     */
    @XmlElement(name = "description")
    public String getDescription();

    /**
     * Set the description
     * 
     * @param description
     */
    public void setDescription(String description);
    
    
    /**
     * Get the picurl
     * 
     * @return
     */
    @XmlElement(name = "picurl")
    public String getPicurl();

    /**
     * Set the picurl
     * 
     * @param picurl
     */
    public void setPicurl(String picurl);
    
    
    
    @XmlElement(name = "processrecoveryId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    public KapuaId getProcessrecoveryId();
    
    
    
    
    public void setProcessrecoveryId(KapuaId getProcessrecoveryId);
    
    
  
	      
}
