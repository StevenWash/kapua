package org.eclipse.kapua.service.wxaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.kapua.model.query.KapuaListResult;

@XmlRootElement(name = "wxAccountListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = WxAccountXmlRegistry.class,factoryMethod = "newWxAccountListResult")
public interface WxAccountListResult extends KapuaListResult<WxAccount> {
	
  

   
   
}
