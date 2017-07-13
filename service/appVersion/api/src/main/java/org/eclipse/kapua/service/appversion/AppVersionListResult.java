package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.kapua.model.query.KapuaListResult;

@XmlRootElement(name = "appVersionListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(factoryClass = AppVersionXmlRegistry.class,factoryMethod = "newAppVersionListResult")
public interface AppVersionListResult extends KapuaListResult<AppVersion> {
	
  

   
   
}
