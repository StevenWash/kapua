package org.eclipse.kapua.service.weather.internal;

import java.util.Map;
import org.eclipse.kapua.service.weather.BaseWeatherInfo;
import net.sf.json.JSONObject;

public class SinaWeatherInfo extends BaseWeatherInfo{
	
	
	public String buildJsonMsg() {
		String result = null;
		if(!city.equals("")){
			JSONObject jsonObject = JSONObject.fromObject(this);
			result = jsonObject.toString();
		}
		return result;
	}
	
	
	public void doParser(Map<String,String> map){
		/** 城市名*/
		String city = map.get(SinaWeatherTag.CITY);
		this.setCity(city);
		
		/** 白天天气状况*//** 夜晚天气状况*//** 白天天气状况拼音*//** 夜晚天气状况拼音*/
		String day_weather = map.get(SinaWeatherTag.DAY_WEATHER);
		String night_weather = map.get(SinaWeatherTag.NIGHT_WEATHER);
		String weatherNightPinyin = map.get(SinaWeatherTag.NIGHT_WEATHER_PINYIN);
		String weatherDayPinyin = map.get(SinaWeatherTag.DAY_WEATHER_PINYIN);
		this.setStatus1(day_weather);
		this.setStatus2(night_weather);
		this.setFigure1(weatherDayPinyin);
		this.setFigure2(weatherNightPinyin);
		
		/** 白天风向*//** 夜晚风向*//** 白天风力*//** 夜晚风力*/
		String day_wind_direction = map.get(SinaWeatherTag.DAY_WIND_DIRECTION);
		String night_wind_direction = map.get(SinaWeatherTag.NIGHT_WIND_DIRECTION);
		String day_wind_power = map.get(SinaWeatherTag.DAY_WIND_POWER);
		String night_wind_power = map.get(SinaWeatherTag.NIGHT_WIND_POWER);
		this.setDirection1(day_wind_direction);
		this.setDirection2(night_wind_direction);
		this.setPower1(day_wind_power);
		this.setPower2(night_wind_power);
		
		/** 白天气温*//** 夜晚气温*/
		String day_temperature = map.get(SinaWeatherTag.DAY_TEMPERATURE);
		String night_temperature = map.get(SinaWeatherTag.NIGHT_TEMPERATURE);
		this.setTemperature1(day_temperature);
		this.setTemperature2(night_temperature);
		
		/** 白天体感温度*//** 夜晚体感温度*//** 体感温度等级*//** 体感温度指数*//** 体感温度描述*/
		String day_sendible_temperature = map.get(SinaWeatherTag.DAY_SENDIBLE_TEMPERATURE);
		String night_sendible_temperature = map.get(SinaWeatherTag.NIGHT_SENDIBLE_TEMPERATURE);
		String sendibleLevel = map.get(SinaWeatherTag.SENDIBLE_LEVEL);
		String sendibleIndex = map.get(SinaWeatherTag.SENDIBLE_INDEX);
		String sendibleDescr = map.get(SinaWeatherTag.SENDIBLE_DESCRIPTION);
		this.setTgd1(day_sendible_temperature);
		this.setTgd2(night_sendible_temperature);
		this.setSsd(sendibleLevel);
		this.setSsd_l(sendibleIndex);
		this.setSsd_s(sendibleDescr);
		
		/** 紫外线等级*//** 紫外线指数*//** 紫外线描述*/
		String ultravioletLevel = map.get(SinaWeatherTag.ULTRAVIOLET_LEVEL);
		String ultravioletIndex = map.get(SinaWeatherTag.ULTRAVIOLET_INDEX);
		String ultravioletDescr = map.get(SinaWeatherTag.ULTRAVIOLET_DESCRIPTION);
		this.setZwx(ultravioletLevel);
		this.setZwx_l(ultravioletIndex);
		this.setZwx_s(ultravioletDescr);
		
		/** 空调等级*//** 空调指数*//** 空调描述*/
		String airconditionLevel = map.get(SinaWeatherTag.AIR_CONDITIONING_LEVEL);
		String airconditionIndex = map.get(SinaWeatherTag.AIR_CONDITIONING_INDEX);
		String airconditionDescr = map.get(SinaWeatherTag.AIR_CONDITIONING_DESCRIPTION);
		this.setKtk(airconditionLevel);
		this.setKtk_l(airconditionIndex);
		this.setKtk_s(airconditionDescr);
		
		/** 污染物等级*//** 污染物指数*//** 污染物描述*/
		String pollutionLevel = map.get(SinaWeatherTag.POLLUTION_LEVEL);
		String pollutionIndex = map.get(SinaWeatherTag.POLLUTION_INDEX);
		String pollutionDescr = map.get(SinaWeatherTag.POLLUTION_DESCRIPTION);
		this.setPollution(pollutionLevel);
		this.setPollution_l(pollutionIndex);
		this.setPollution_s(pollutionDescr);
		
		/** 洗车等级*//** 洗车指数*//** 洗车描述*/
		String vehiclecleaningLevel = map.get(SinaWeatherTag.VEHICLE_CLEANING_LEVEL);
		String vehiclecleaningIndex = map.get(SinaWeatherTag.VEHICLE_CLEANING_INDEX);
		String vehiclecleaningDescr = map.get(SinaWeatherTag.VEHICLE_CLEANING_DESCRIPTION);
		this.setXcz(vehiclecleaningLevel);
		this.setXcz_l(vehiclecleaningIndex);
		this.setXcz_s(vehiclecleaningDescr);
		
		/** 穿衣等级*//** 穿衣指数*//** 穿衣描述*/
		String dressingLevel = map.get(SinaWeatherTag.DRESSING_LEVEL);
		String dressingIndex = map.get(SinaWeatherTag.DRESSING_INDEX);
		String dressingDescr = map.get(SinaWeatherTag.DRESSING_DESCRIPTION);
		this.setChy(dressingLevel);
		this.setChy_l(dressingIndex);
		this.setChy_shuoming(dressingDescr);
		
		/** 感冒等级*//** 感冒指数*//** 感冒描述*/
		String coldLevel = map.get(SinaWeatherTag.COLD_LEVEL);
		String coldIndex = map.get(SinaWeatherTag.COLD_INDEX);
		String coldDescr = map.get(SinaWeatherTag.COLD_DESCRIPTION);
		this.setGm(coldLevel);
		this.setGm_l(coldIndex);
		this.setGm_s(coldDescr);
		
		/** 运动等级*//** 运动指数*//** 运动描述*/
		String exerciseLevel = map.get(SinaWeatherTag.EXERCISE_LEVEL);
		String exerciseIndex = map.get(SinaWeatherTag.EXERCISE_INDEX);
		String exerciseDescr = map.get(SinaWeatherTag.EXERCISE_DESCRIPTION);
		this.setYd(exerciseLevel);
		this.setYd_l(exerciseIndex);
		this.setYd_s(exerciseDescr);
		
		/** 天气更新时间*//** 生活信息更新时间*//** 指数更新时间*/
		String savedate_weather = map.get(SinaWeatherTag.SAVEDATE_WEATHER);
		String savedate_life = map.get(SinaWeatherTag.SAVEDATE_LIFE);
		String savedate_zhishu = map.get(SinaWeatherTag.SAVEDATE_ZHISHU);
		this.setSavedate_weather(savedate_weather);
		this.setSavedate_life(savedate_life);
		this.setSavedate_zhishu(savedate_zhishu);
	}
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getFigure1() {
		return figure1;
	}

	public void setFigure1(String figure1) {
		this.figure1 = figure1;
	}

	public String getFigure2() {
		return figure2;
	}

	public void setFigure2(String figure2) {
		this.figure2 = figure2;
	}

	public String getDirection1() {
		return direction1;
	}

	public void setDirection1(String direction1) {
		this.direction1 = direction1;
	}

	public String getDirection2() {
		return direction2;
	}

	public void setDirection2(String direction2) {
		this.direction2 = direction2;
	}

	public String getPower1() {
		return power1;
	}

	public void setPower1(String power1) {
		this.power1 = power1;
	}

	public String getPower2() {
		return power2;
	}

	public void setPower2(String power2) {
		this.power2 = power2;
	}

	public String getTemperature1() {
		return temperature1;
	}

	public void setTemperature1(String temperature1) {
		this.temperature1 = temperature1;
	}

	public String getTemperature2() {
		return temperature2;
	}

	public void setTemperature2(String temperature2) {
		this.temperature2 = temperature2;
	}

	public String getTgd1() {
		return tgd1;
	}

	public void setTgd1(String tgd1) {
		this.tgd1 = tgd1;
	}

	public String getTgd2() {
		return tgd2;
	}

	public void setTgd2(String tgd2) {
		this.tgd2 = tgd2;
	}

	public String getSsd() {
		return ssd;
	}

	public void setSsd(String ssd) {
		this.ssd = ssd;
	}

	public String getSsd_l() {
		return ssd_l;
	}

	public void setSsd_l(String ssd_l) {
		this.ssd_l = ssd_l;
	}

	public String getSsd_s() {
		return ssd_s;
	}

	public void setSsd_s(String ssd_s) {
		this.ssd_s = ssd_s;
	}

	public String getZwx() {
		return zwx;
	}

	public void setZwx(String zwx) {
		this.zwx = zwx;
	}

	public String getZwx_l() {
		return zwx_l;
	}

	public void setZwx_l(String zwx_l) {
		this.zwx_l = zwx_l;
	}

	public String getZwx_s() {
		return zwx_s;
	}

	public void setZwx_s(String zwx_s) {
		this.zwx_s = zwx_s;
	}

	public String getKtk() {
		return ktk;
	}

	public void setKtk(String ktk) {
		this.ktk = ktk;
	}

	public String getKtk_l() {
		return ktk_l;
	}

	public void setKtk_l(String ktk_l) {
		this.ktk_l = ktk_l;
	}

	public String getKtk_s() {
		return ktk_s;
	}

	public void setKtk_s(String ktk_s) {
		this.ktk_s = ktk_s;
	}

	public String getPollution() {
		return pollution;
	}

	public void setPollution(String pollution) {
		this.pollution = pollution;
	}

	public String getPollution_l() {
		return pollution_l;
	}

	public void setPollution_l(String pollution_l) {
		this.pollution_l = pollution_l;
	}

	public String getPollution_s() {
		return pollution_s;
	}

	public void setPollution_s(String pollution_s) {
		this.pollution_s = pollution_s;
	}

	public String getXcz() {
		return xcz;
	}

	public void setXcz(String xcz) {
		this.xcz = xcz;
	}

	public String getXcz_l() {
		return xcz_l;
	}

	public void setXcz_l(String xcz_l) {
		this.xcz_l = xcz_l;
	}

	public String getXcz_s() {
		return xcz_s;
	}

	public void setXcz_s(String xcz_s) {
		this.xcz_s = xcz_s;
	}

	public String getChy() {
		return chy;
	}

	public void setChy(String chy) {
		this.chy = chy;
	}

	public String getChy_l() {
		return chy_l;
	}

	public void setChy_l(String chy_l) {
		this.chy_l = chy_l;
	}

	public String getChy_shuoming() {
		return chy_shuoming;
	}

	public void setChy_shuoming(String chy_shuoming) {
		this.chy_shuoming = chy_shuoming;
	}

	public String getGm() {
		return gm;
	}

	public void setGm(String gm) {
		this.gm = gm;
	}

	public String getGm_l() {
		return gm_l;
	}

	public void setGm_l(String gm_l) {
		this.gm_l = gm_l;
	}

	public String getGm_s() {
		return gm_s;
	}

	public void setGm_s(String gm_s) {
		this.gm_s = gm_s;
	}

	public String getYd() {
		return yd;
	}

	public void setYd(String yd) {
		this.yd = yd;
	}

	public String getYd_l() {
		return yd_l;
	}

	public void setYd_l(String yd_l) {
		this.yd_l = yd_l;
	}

	public String getYd_s() {
		return yd_s;
	}

	public void setYd_s(String yd_s) {
		this.yd_s = yd_s;
	}

	public String getSavedate_weather() {
		return savedate_weather;
	}

	public void setSavedate_weather(String savedate_weather) {
		this.savedate_weather = savedate_weather;
	}

	public String getSavedate_life() {
		return savedate_life;
	}

	public void setSavedate_life(String savedate_life) {
		this.savedate_life = savedate_life;
	}

	public String getSavedate_zhishu() {
		return savedate_zhishu;
	}

	public void setSavedate_zhishu(String savedate_zhishu) {
		this.savedate_zhishu = savedate_zhishu;
	}


	/** 城市名*/
	private String city;
	
	/** 白天天气状况*/
	private String status1;
	/** 夜晚天气状况*/
	private String status2;
	/** 白天天气状况拼音*/
	private String figure1;
	/** 夜晚天气状况拼音*/
	private String figure2;
	
	/** 白天风向*/
	private String direction1;
	/** 夜晚风向*/
	private String direction2;
	/** 白天风力*/
	private String power1;
	/** 夜晚风力*/
	private String power2;
	
	/** 白天气温*/
	private String temperature1;
	/** 夜晚气温*/
	private String temperature2;
	
	/** 白天体感温度*/
	private String tgd1;
	/** 夜晚体感温度*/
	private String tgd2;
	/** 体感温度等级*/
	private String ssd;
	/** 体感温度指数*/
	private String ssd_l;
	/** 体感温度描述*/
	private String ssd_s;
	
	/** 紫外线等级*/
	private String zwx;
	/** 紫外线指数*/
	private String zwx_l;
	/** 紫外线描述*/
	private String zwx_s;
	
	/** 空调等级*/
	private String ktk;
	/** 空调指数*/
	private String ktk_l;
	/** 空调描述*/
	private String ktk_s;
	
	/** 污染物等级*/
	private String pollution;
	/** 污染物指数*/
	private String pollution_l;
	/** 污染物描述*/
	private String pollution_s;
	
	/** 洗车等级*/
	private String xcz;
	/** 洗车指数*/
	private String xcz_l;
	/** 洗车描述*/
	private String xcz_s;
	
	/** 穿衣等级*/
	private String chy;
	/** 穿衣指数*/
	private String chy_l;
	/** 穿衣描述*/
	private String chy_shuoming;
	
	/** 感冒等级*/
	private String gm;
	/** 感冒指数*/
	private String gm_l;
	/** 感冒描述*/
	private String gm_s;
	
	/** 运动等级*/
	private String yd;
	/** 运动指数*/
	private String yd_l;
	/** 运动描述*/
	private String yd_s;
	
	/** 天气更新时间*/
	private String savedate_weather;
	/** 生活信息更新时间*/
	private String savedate_life;
	/** 指数更新时间*/
	private String savedate_zhishu;
	@Override
	public void doParser(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
