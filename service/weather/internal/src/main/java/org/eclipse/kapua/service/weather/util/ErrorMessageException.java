package org.eclipse.kapua.service.weather.util;



public class ErrorMessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397309216245775326L;
	
	String errorMessage;
	
	public ErrorMessageException(){
		ErrorResultList res=new ErrorResultList();
		res.setErrorMessage("city is null");
		net.sf.json.JSONObject jsonRes = net.sf.json.JSONObject.fromObject(res);
		
		errorMessage=jsonRes.toString();;
	}
	
	public String warnMess(){
	        return errorMessage;
	}


}
