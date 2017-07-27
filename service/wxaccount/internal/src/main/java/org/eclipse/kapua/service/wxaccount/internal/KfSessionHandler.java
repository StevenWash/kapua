package org.eclipse.kapua.service.wxaccount.internal;

import java.util.Map;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class KfSessionHandler  extends AbstractHandler {
	
	  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
		      Map<String, Object> context, WxMpService wxMpService,
		            WxSessionManager sessionManager) {
		    //TODO 对会话做处理
		    return null;
		  }

}
