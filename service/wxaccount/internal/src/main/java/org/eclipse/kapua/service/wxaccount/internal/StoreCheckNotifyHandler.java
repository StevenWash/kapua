package org.eclipse.kapua.service.wxaccount.internal;

import java.util.Map;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class StoreCheckNotifyHandler extends AbstractHandler {
	
	 public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
		      Map<String, Object> context, WxMpService wxMpService,
		      WxSessionManager sessionManager) {
		    // TODO 处理门店审核事件
		    return null;
		  }

}
