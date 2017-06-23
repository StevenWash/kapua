package org.eclipse.kapua.service.weather.internal;

import net.sf.json.JSONObject;

import org.eclipse.kapua.service.weather.BaseIpInfo;

public class SinaIpInfo extends BaseIpInfo{
	private String start;
	private String end;
	private String country;
	private String province;
	private String city;
	private String district;
	private String isp;
	private String type;
	private String desc;
	
	
	

	

	@Override
	public void doParser(String content) {
		
		JSONObject jsonObject = JSONObject.fromObject(content);
		this.start = jsonObject.getString("start");
		this.end = jsonObject.getString("end");
		this.country = jsonObject.getString("country");
		this.province = jsonObject.getString("province");
		this.city = jsonObject.getString("city");
		this.district = jsonObject.getString("district");
		this.isp = jsonObject.getString("isp");
		this.type = jsonObject.getString("type");
		this.desc = jsonObject.getString("desc");
		
	}

	@Override
	public String buildJsonMsg() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		
		return jsonObject.toString();
	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}




	
	


	


}
