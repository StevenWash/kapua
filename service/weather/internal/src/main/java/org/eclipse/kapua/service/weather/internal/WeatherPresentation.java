package org.eclipse.kapua.service.weather.internal;

public class WeatherPresentation {
	
	private String location;
	
	private String date;
	
	private String high;
	
	private String low;
	
	private String status;
	
	private String text;
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("date:");
		builder.append(date);
		builder.append(", location:");
		builder.append(location);
		builder.append(", high:");
		builder.append(high);
		builder.append(", low:");
		builder.append(low);
		builder.append(", status:");
		builder.append(status);
		builder.append(", text:");
		builder.append(text);
		
		return builder.toString();
	}
	

}
