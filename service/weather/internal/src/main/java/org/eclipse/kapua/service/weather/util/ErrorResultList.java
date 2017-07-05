package org.eclipse.kapua.service.weather.util;

public class ErrorResultList {
	
	 private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	 
	 
	public String toString(){
		StringBuilder builder=new StringBuilder();
		builder.append("errorMessage:");
		builder.append(errorMessage);
		return builder.toString();
	}
	 

}
