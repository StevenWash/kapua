package org.eclipse.kapua.service.weather.util;

import org.eclipse.kapua.service.weather.YahooWeather;
import org.eclipse.kapua.service.weather.internal.YahooWeatherImpl;

import net.sf.json.JSONObject;

public class ErrorMessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397309216245775326L;
	
	String errorMessage;
	
	public ErrorMessageException(){
		
		ErrorResultList  res=new ErrorResultList();
		
		
	   res.setErrorMessage("city is null");
		
		JSONObject jsonRes =JSONObject.fromObject(res);
		
		errorMessage=jsonRes.toString();
	}
	
	public String warnMess(){
		
	        return errorMessage;
	}


}
