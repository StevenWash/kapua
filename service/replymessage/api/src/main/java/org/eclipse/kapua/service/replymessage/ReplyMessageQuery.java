package org.eclipse.kapua.service.replymessage;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import org.eclipse.kapua.model.query.KapuaQuery;

/**
 * ReplyMessage query definition.
 * 
 * @since 1.0
 * ReplyMessage
 */
@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = ReplyMessageXmlRegistry.class, factoryMethod = "newQuery")
public interface ReplyMessageQuery extends KapuaQuery<ReplyMessage> {
    
}
