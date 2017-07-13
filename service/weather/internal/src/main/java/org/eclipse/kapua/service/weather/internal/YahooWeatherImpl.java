package org.eclipse.kapua.service.weather.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.kapua.service.weather.Forecast;
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
	
	
	private String high_temp;
	
	
	private String low_temp;
	
	
	private String text;
	
	private String humidity;
	
	private String wind_speed;
	
	private String direction; //风向
	
	private String pubdate;
	
	private ArrayList<Forecast> items;;
	
	
	
	
    public YahooWeatherImpl(){
    	
      items = new ArrayList<>();
      
	}
    
   
	
	
	public YahooWeatherImpl(String city, String country, String region,
			String date, String day, String high_temp, String low_temp, String text,String humidity,String wind_speed,String direction,String pubdate) {
		this.city = city;
		this.country = country;
		this.region = region;
		this.date = date;
		this.day = day;
		this.high_temp = high_temp;
		this.low_temp = low_temp;
		this.text = text;
		this.humidity=humidity;
		this.wind_speed=wind_speed;
		this.direction=direction;
		this.pubdate=pubdate;
		
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

	
	public String getLow_temp() {
		
		return low_temp;
	}

	
	public void setLow_temp(String low_temp) {
		
		this.low_temp=low_temp;
		
	}

	
	public String getHigh_temp() {
		
		return high_temp;
	}

	
	public void setHigh_temp(String high_temp) {
		
		this.high_temp=high_temp;
		
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


	@Override
	public String getHumidity() {
		// TODO Auto-generated method stub
		return humidity;
	}


	@Override
	public void setHumidity(String humidity) {
		// TODO Auto-generated method stub
		this.humidity=humidity;
		
	}


	@Override
	public String getWind_speed() {
		// TODO Auto-generated method stub
		return wind_speed;
	}


	@Override
	public void setWind_speed(String wind_speed) {
		// TODO Auto-generated method stub
		this.wind_speed=wind_speed;
		
	}
	 //direction
	@Override
	public String getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}


	@Override
	public void setDirection(String direction) {
		// TODO Auto-generated method stub
		this.direction=direction;
		
	}


	public String getPubdate() {
		return pubdate;
	}


	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}



    @Override
    public List<Forecast> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public void addItems(Collection<Forecast> items) {
        this.items.addAll(items);
    }



	
	

}
