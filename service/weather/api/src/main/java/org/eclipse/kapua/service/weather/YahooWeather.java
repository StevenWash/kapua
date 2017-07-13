package org.eclipse.kapua.service.weather;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name = "yahooWeather")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
		"city",
		"region",
        "country",
        "day",
        "date",
        "low_temp",
        "high_temp",
        "text",
        "humidity",
        "wind_speed",
        "direction",
        "pubdate",
        "items"
       
        })
public interface YahooWeather extends Serializable{
	 
	 public static final String TYPE = "yahooWeather";
	 
	    @XmlTransient
	    public default String getType() {
	        return TYPE;
	    }
	    
	   
	    
	    
	    @XmlElement(name = "city")
	    public String getCity();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setCity(String  city);
	    
	    
	    @XmlElement(name = "region")
	    public String getRegion();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setRegion(String  region);
	    
	    
	    @XmlElement(name = "country")
	    public String getCountry();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setCountry(String  country);
	    
	    
	    @XmlElement(name = "day")
	    public String getDay();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setDay(String  day);
	    
	    
	    @XmlElement(name = "low_temp")
	    public String getLow_temp();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setLow_temp(String  low_temp);
	    
	    
	    @XmlElement(name = "high_temp")
	    public String getHigh_temp();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setHigh_temp(String  high_temp);
	    
	    
	    @XmlElement(name = "text")
	    public String getText();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setText(String  text);
	    
	    
	    @XmlElement(name = "date")
	    public String getDate();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setDate(String  date);
	    
	    //humidity
	    @XmlElement(name = "humidity")
	    public String getHumidity();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setHumidity(String  humidity);
	    
	    @XmlElement(name = "wind_speed")
	    public String getWind_speed();

	    /**
	     * Set the weather's province
	     * 
	     * @param province  direction
	     */
	    public void setWind_speed(String  wind_speed);
	    
	    
	    @XmlElement(name = "direction")
	    public String getDirection();

	    /**
	     * Set the weather's province
	     * 
	     * @param province  direction
	     */
	    public void setDirection(String  direction);
	    
	    
	    @XmlElement(name = "pubdate")
	    public String getPubdate();

	    /**
	     * Set the weather's province
	     * 
	     * @param province  direction
	     */
	    public void setPubdate(String  pubdate);
	    
	   
	    @XmlElementWrapper(name = "items")
	    @XmlElement(name = "item")
	    public List<Forecast> getItems();
	    
	    
	    public void addItems(Collection<Forecast> items);
	    
	    
	    

	    

}
