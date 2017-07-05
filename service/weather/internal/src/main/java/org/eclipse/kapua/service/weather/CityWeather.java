package org.eclipse.kapua.service.weather;

import javax.persistence.*;
import org.eclipse.kapua.model.id.KapuaId;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the city_weather database table.
 * 
 */
@Entity
@Table(name="city_weather")
@NamedQuery(name="CityWeather.findAll", query="SELECT c FROM CityWeather c")
public class CityWeather implements Weather {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String area;

	private String city;

	@Column(name="city_code")
	private String cityCode;

	@Column(name="created_by")
	private BigInteger createdBy;

	@Column(name="created_on")
	private Timestamp createdOn;

	private String province;

	@Column(name="scope_id")
	private BigInteger scopeId;

	public CityWeather() {
	}

	/*public String getId() {
		return this.id;
	}*/

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public BigInteger getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public BigInteger getScopeId() {
		return this.scopeId;
	}

	public void setScopeId(BigInteger scopeId) {
		this.scopeId = scopeId;
	}

	@Override
	public KapuaId getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(KapuaId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCity_code() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCity_code(String city_code) {
		// TODO Auto-generated method stub
		
	}

}