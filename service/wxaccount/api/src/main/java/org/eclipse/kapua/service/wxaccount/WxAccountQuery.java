package org.eclipse.kapua.service.wxaccount;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import org.eclipse.kapua.model.query.KapuaQuery;

/**
 * AppVersion query definition.
 * 
 * @since 1.0
 * 
 */
@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = WxAccountXmlRegistry.class, factoryMethod = "newQuery")
public interface WxAccountQuery extends KapuaQuery<WxAccount> {
    
}
