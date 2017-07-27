package org.eclipse.kapua.service.wxaccount.internal;

import java.util.Map;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class MenuHandler extends AbstractHandler  {
	@Override
	 public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
	            WxSessionManager sessionManager) {
	        WeiXinServiceImpl weixinService = (WeiXinServiceImpl) wxMpService;

	        if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
	            String key = wxMessage.getEventKey();        
	            if (key.equals("hai")) {
	                String content = "";
	                StringBuffer contentMsg = new StringBuffer();
	                contentMsg.append("回复下列关键词，体验相应服务").append("\n\n");
	                contentMsg.append("1 、登录").append("\n");
	                contentMsg.append("2 、注册").append("\n");
	                contentMsg.append("3 、修改密码").append("\n");
	                content = content + contentMsg.toString();
	                return new TextBuilder().build(content, wxMessage, weixinService);
	            }
	        }

	        return null;

	    }



}
