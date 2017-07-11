package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import org.eclipse.kapua.model.query.KapuaQuery;

/**
 * Account query definition.
 * 
 * @since 1.0
 * 
 */
@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = AppVersionXmlRegistry.class, factoryMethod = "newQuery")
public interface AppVersionQuery extends KapuaQuery<AppVersion> {
    
}
