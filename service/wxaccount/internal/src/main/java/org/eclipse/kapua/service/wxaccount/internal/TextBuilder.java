package org.eclipse.kapua.service.wxaccount.internal;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

public class TextBuilder extends AbstractBuilder {
	
	  public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
		      WeiXinServiceImpl service)   {
		    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(content)
		        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
		        .build();
		    return m;
		  }

}
