package org.eclipse.kapua.service.weather.internal;


import org.eclipse.kapua.service.weather.BaseResultMessage;
/**
 * 普通回复
 * @author L
 *
 */
public class NormalResult extends BaseResultMessage{
	private String result;

	@Override
	protected String BuildSpecialResult() {
		return result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
