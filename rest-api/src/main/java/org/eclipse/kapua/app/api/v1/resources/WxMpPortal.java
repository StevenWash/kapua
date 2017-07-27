package org.eclipse.kapua.app.api.v1.resources;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.kapua.service.wxaccount.internal.WeiXinServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("WxMpPortals")
@Path("wechat/portal")
public class WxMpPortal {
	
	  private final Logger logger = LoggerFactory.getLogger(this.getClass());
	  
	  @Context HttpServletRequest request;
	  
	  WeiXinServiceImpl  wxService=new WeiXinServiceImpl();
	
    @ApiOperation(value = "Get an ",  //
    notes = "Returns the  specified by the  path parameter.",  //
    response = String.class)
	@GET
	@Path("{name}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML, MediaType.TEXT_PLAIN })
	public String authGet(
		@ApiParam(value = "The name of the requested .", required = true) @PathParam("name") String name,
		@ApiParam(value = "The signature of the requested .", required = true) @QueryParam("signature") String signature,	
	    @ApiParam(value = "The timestamp of the requested .", required = true) @QueryParam("timestamp") String timestamp, //
	    @ApiParam(value = "The nonce of the requested ", required = true) @QueryParam("nonce") String nonce,
	    @ApiParam(value = "The echostr of the requested ", required = true) @QueryParam("echostr") String echostr
        ){
    	System.out.println("__________________________________-");
    	 this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
    	 System.out.println( signature+" "+timestamp+" "+nonce+" "+echostr);
    	 
    	  if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
    	      throw new IllegalArgumentException("请求参数非法，请核实!");
    	    }

    	    if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
    	      return echostr;
    	    }

    	    return "非法请求";
    	  }
    
    
    
    @ApiOperation(value = "Get the WxMpPortals",  //
    	    notes = "Returns the  specified by the  path parameter.",  //
    	    response = String.class)
    		@POST
    	    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    		@Produces({ MediaType.TEXT_HTML, MediaType.TEXT_PLAIN })
    		public String post(
    			@ApiParam(value = "The name of the requested .", required = true) @QueryParam("signature") String signature,
    			@ApiParam(value = "The signature of the requested .", required = true) @QueryParam("encrypt_type") String encType,	
    		    @ApiParam(value = "The timestamp of the requested .", required = true) @QueryParam("msg_signature") String msgSignature, //
    		    @ApiParam(value = "The timestamp of the requested .") @QueryParam("timestamp") String timestamp,
    		    @ApiParam(value = "The nonce of the requested ") @QueryParam("nonce") String nonce
    	        ) {
    	    	System.out.println("__________________________________-");
    	    	
    	    	String requestBody=null;
				try {
					/*InputStream  body=request.getInputStream();
					DataInputStream input = new DataInputStream(body); 
					requestBody = input.readUTF();*/
					
					
					byte[] bytes = new byte[1024 * 1024];  
		            InputStream is = request.getInputStream();  
		  
		            int nRead = 1;  
		            int nTotalRead = 0;  
		            while (nRead > 0) {  
		                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);  
		                if (nRead > 0)  
		                    nTotalRead = nTotalRead + nRead;  
		            }  
		            requestBody = new String(bytes, 0, nTotalRead, "utf-8");  
		            System.out.println("requestBody:" + requestBody);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				System.out.println("::::::::::"+requestBody);
    	    	 this.logger.info(
    	    		        "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
    	    		            + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
    	    		        signature, encType, msgSignature, timestamp, nonce, requestBody);

    	    		    if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
    	    		      throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
    	    		    }

    	    		    String out = null;
    	    		    
    	    		    if (encType == null) {
    	    		      // 明文传输的消息
    	    		      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
    	    		      WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
    	    		      if (outMessage == null) {
    	    		        return "";
    	    		      }
    	    		      out = outMessage.toXml();
    	    		     
    	    		      
    	    		     } else if ("aes".equals(encType)) {
    	    		      // aes加密的消息
    	    		      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
    	    		          this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msgSignature);
    	    		      this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
    	    		      WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
    	    		      if (outMessage == null) {
    	    		        return "";
    	    		      }

    	    		      out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
    	    		    }

    	    		    this.logger.debug("\n组装回复信息：{}", out);

    	    		    return out;
    	    	  }
    
    
    
	
    
    protected WeiXinServiceImpl getWxService() {
        return this.wxService;
      }
}
