package org.eclipse.kapua.service.weather.internal;

import org.eclipse.kapua.service.weather.util.HttpUtil;
import org.eclipse.kapua.service.weather.util.ErrorMessageException;
import org.eclipse.kapua.service.weather.BaseIpInfo;
import org.eclipse.kapua.service.weather.BaseIpService;

public class SinaIpService implements BaseIpService {
	String result = "";
	
	@Override
	public String getInformation(String ip)throws Exception{
		try {
		            String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip;
			
				
					String httpResult = HttpUtil.getHttpConnHtml(url);
					
					
					BaseIpInfo ipInfo = new SinaIpInfo();
					
					ipInfo.doParser(httpResult);
					
					result = ipInfo.buildJsonMsg();
				
					
	        } catch (Exception e) {
			
				throw new ErrorMessageException();
	        	
			}
			
			
			
		
		
		return result;
	}

}
