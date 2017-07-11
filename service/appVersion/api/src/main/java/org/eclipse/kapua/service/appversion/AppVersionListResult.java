package org.eclipse.kapua.service.appversion;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.query.KapuaListResult;


@XmlRootElement(name = "appVersionListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"size", "items" })
public interface AppVersionListResult extends KapuaListResult<AppVersion> {
	
  

   
   
}
