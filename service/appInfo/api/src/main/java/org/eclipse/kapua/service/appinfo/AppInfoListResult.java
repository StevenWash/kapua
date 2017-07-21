package org.eclipse.kapua.service.appinfo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.query.KapuaListResult;

@XmlRootElement(name = "appInfoListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"size", "items" })
public interface AppInfoListResult  extends KapuaListResult<AppInfo> {
	
  



 
   
}
