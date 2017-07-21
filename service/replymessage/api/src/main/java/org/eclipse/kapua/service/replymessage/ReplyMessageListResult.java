package org.eclipse.kapua.service.replymessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.kapua.model.query.KapuaListResult;

@XmlRootElement(name = "replyMessageListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = ReplyMessageXmlRegistry.class,factoryMethod = "newReplyMessageListResult")
public interface ReplyMessageListResult extends KapuaListResult<ReplyMessage> {
	
  

   
   
}
