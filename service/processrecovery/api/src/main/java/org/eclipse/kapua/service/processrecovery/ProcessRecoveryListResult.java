package org.eclipse.kapua.service.processrecovery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.kapua.model.query.KapuaListResult;

@XmlRootElement(name = "processRecoveryListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = ProcessRecoveryXmlRegistry.class,factoryMethod = "newProcessRecoveryListResult")
public interface ProcessRecoveryListResult extends KapuaListResult<ProcessRecovery> {
	
  

   
   
}
