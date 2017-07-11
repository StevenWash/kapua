package org.eclipse.kapua.service.weather;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
        "low",
        "high",
        "text"
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
	    
	    
	    @XmlElement(name = "low")
	    public String getLow();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setLow(String  low);
	    
	    
	    @XmlElement(name = "high")
	    public String getHigh();

	    /**
	     * Set the weather's province
	     * 
	     * @param province
	     */
	    public void setHigh(String  high);
	    
	    
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

}
