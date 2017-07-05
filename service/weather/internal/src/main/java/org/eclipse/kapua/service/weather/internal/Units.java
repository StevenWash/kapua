package org.eclipse.kapua.service.weather.internal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.kapua.service.weather.DegreeUnit;
import org.eclipse.kapua.service.weather.DistanceUnit;
import org.eclipse.kapua.service.weather.PressureUnit;
import org.eclipse.kapua.service.weather.SpeedUnit;


/**
 * Units for various aspects of the forecast.
 * @author "Federico De Faveri defaveri@gmail.com"
 */
@XmlRootElement
public class Units {
	
	/**
	 * Temperature unit.
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(DegreeUnitAdapter.class)
	private DegreeUnit temperature;
	
	/**
	 * Distance unit.
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(DistanceUnitAdapter.class)
	private DistanceUnit distance;
	
	/**
	 * Units of barometric pressure.
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(PressureUnitAdapter.class)
	private PressureUnit pressure;
	
	/**
	 * Units of speed.
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(SpeedUnitAdapter.class)
	private SpeedUnit speed;
	
	public Units(){}

	/**
	 * @param temperature
	 * @param distance
	 * @param pressure
	 * @param speed
	 */
	public Units(DegreeUnit temperature, DistanceUnit distance,
			PressureUnit pressure, SpeedUnit speed) {
		this.temperature = temperature;
		this.distance = distance;
		this.pressure = pressure;
		this.speed = speed;
	}

	/**
	 * Returns the temperature unit.
	 * @return the temperature
	 */
	public DegreeUnit getTemperature() {
		return temperature;
	}

	/**
	 * Returns the distance unit.
	 * @return the distance
	 */
	public DistanceUnit getDistance() {
		return distance;
	}

	/**
	 * Returns the units of barometric pressure.
	 * @return the pressure
	 */
	public PressureUnit getPressure() {
		return pressure;
	}

	/**
	 * Returns the units of speed.
	 * @return the speed
	 */
	public SpeedUnit getSpeed() {
		return speed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("temperature=");
		builder.append(temperature);
		builder.append(", distance=");
		builder.append(distance);
		builder.append(", pressure=");
		builder.append(pressure);
		builder.append(", speed=");
		builder.append(speed);
		
		return builder.toString();
	}
}
