package org.eclipse.kapua.service.weather;

import java.util.HashMap;
import java.util.Map;
/**
 * 回复 信息码 管理类
 * @author L
 *
 */
public class ErrorMessageHelper {
	private static Map<Integer, String> map  = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(-1, "{\"errcode\":-1,\"errmsg\":\"error\"}");
			put(0, "{\"errcode\":0,\"errmsg\":\"OK\"}");
			put(1001, "{\"errcode\":1001,\"errmsg\":\"input data error\"}");
			put(1002, "{\"errcode\":1002,\"errmsg\":\"invalid key\"}");
			put(1003, "{\"errcode\":1003,\"errmsg\":\"nodata\"}");
			
		}
	};
	
	public static String builtErrorMsg(int errorcode) {
		String result = map.get(errorcode);
		
		if (result == null) {
			result = map.get(-1);
		}
		
		return result;
	}
}
