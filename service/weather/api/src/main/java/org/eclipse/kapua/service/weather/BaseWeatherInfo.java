package org.eclipse.kapua.service.weather;

import java.util.Map;

public abstract class BaseWeatherInfo {
	/*
	 * api返回信息解释方法
	 */
	public abstract void doParser(Map<String,String> map) throws Exception;
	
	
	public abstract void doParser(Object object) throws Exception;
	
	/*
	 * 对api处理完成后，生成json体信息
	 */
	public abstract String buildJsonMsg();
}
