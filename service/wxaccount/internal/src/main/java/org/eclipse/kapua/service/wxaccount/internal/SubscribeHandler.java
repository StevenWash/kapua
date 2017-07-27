package org.eclipse.kapua.service.wxaccount.internal;

import java.util.Map;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;



public class SubscribeHandler  extends AbstractHandler {
	
	  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
	            WxSessionManager sessionManager) throws WxErrorException {
	        WeiXinServiceImpl weixinService = (WeiXinServiceImpl) wxMpService;
	        if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
	            String eventType = wxMessage.getEvent();
	            if (eventType.equals(WxConsts.EVT_SUBSCRIBE)) {
	                String content = "感谢关注智居科技！\n";
	                StringBuffer contentMsg = new StringBuffer();
	                contentMsg.append("您还可以回复下列数字，体验相应服务").append("\n\n");
	                contentMsg.append("1 、 用户注册").append("\n");
	                contentMsg.append("2 、 智居服务").append("\n");
	                contentMsg.append("3 、 智居介绍").append("\n");
	                content = content + contentMsg.toString();
	                return new TextBuilder().build(content, wxMessage, weixinService);
	            } else if (eventType.equals(WxConsts.EVT_UNSUBSCRIBE)) {

	            }

	        }
	        return null;

	    }

	    /**
	     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
	     */
	    protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
	        // TODO
	        return null;
	    }

}
