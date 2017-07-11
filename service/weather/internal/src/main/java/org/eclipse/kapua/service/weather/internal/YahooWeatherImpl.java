package org.eclipse.kapua.service.weather.internal;

import org.eclipse.kapua.service.weather.YahooWeather;

public class YahooWeatherImpl implements YahooWeather {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8621374996417182137L;


	private String city;
	
	
	private String country;
	
	
	private String region;
	
	
	private String date;
	
	
	private String day;
	
	
	private String high;
	
	
	private String low;
	
	
	private String text;
	
	
    public YahooWeatherImpl(){
		
	}
	
	
	public YahooWeatherImpl(String city, String country, String region,
			String date, String day, String high, String low, String text) {
		this.city = city;
		this.country = country;
		this.region = region;
		this.date = date;
		this.day = day;
		this.high = high;
		this.low = low;
		this.text = text;
	}
	
	
	public String getCity() {
		
		return city;
	}

	
	public void setCity(String city) {
		
		this.city=city;
		
	}

	
	public String getRegion() {
		
		return region;
	}

	
	public void setRegion(String region) {
		
		this.region=region;
		
	}

	
	public String getCountry() {
		
		return country;
	}

	
	public void setCountry(String country) {
		
		this.country=country;
		
	}

	
	public String getDay() {
		
		return day;
	}

	
	public void setDay(String day) {
		
		this.day=day;
		
	}

	
	public String getLow() {
		
		return low;
	}

	
	public void setLow(String low) {
		
		this.low=low;
		
	}

	
	public String getHigh() {
		
		return high;
	}

	
	public void setHigh(String high) {
		
		this.high=high;
		
	}

	
	public String getText() {
		
		return text;
	}

	
	public void setText(String text) {
		
		this.text=text;
		
	}

	
	public String getDate() {
		
		return date;
	}

	
	public void setDate(String date) {
		
		this.date=date;
		
	}

}
