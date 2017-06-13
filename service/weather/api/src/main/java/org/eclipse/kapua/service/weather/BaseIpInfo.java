package org.eclipse.kapua.service.weather;

public abstract class BaseIpInfo {
	/*
	 * api返回信息解释方法
	 */
	public abstract void doParser(String content) throws Exception;
	
	/*
	 * 对api处理完成后，生成json体信息
	 */
	public abstract String buildJsonMsg();
}
