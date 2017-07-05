package org.eclipse.kapua.service.weather;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.kapua.model.query.KapuaListResult;
import org.eclipse.kapua.model.query.KapuaQuery;

@XmlRootElement(name = "weatherListResult")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"size", "items" })
public interface WeatherListResult extends Serializable {
	
  

    /**
     * Return the result list
     * 
     * @return
     */
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<String> getItems();

 
    public String getItem(int i);
    /**
     * Return the result list size
     * 
     * @return
     */
    @XmlElement(name = "size")
    public int getSize();



    /**
     * Add items to the result list
     * 
     * @param items
     */
    public void addItems(List<String> item);

   
   
}
