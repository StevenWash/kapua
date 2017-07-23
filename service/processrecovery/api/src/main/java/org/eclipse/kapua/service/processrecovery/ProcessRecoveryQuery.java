package org.eclipse.kapua.service.processrecovery;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import org.eclipse.kapua.model.query.KapuaQuery;

/**
 * ProcessRecovery query definition.
 * 
 * @since 1.0
 * ProcessRecovery
 */
@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = ProcessRecoveryXmlRegistry.class, factoryMethod = "newQuery")
public interface ProcessRecoveryQuery extends KapuaQuery<ProcessRecovery> {
    
}
