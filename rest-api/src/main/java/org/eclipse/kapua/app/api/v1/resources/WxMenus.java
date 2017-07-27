package org.eclipse.kapua.app.api.v1.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import org.eclipse.kapua.service.wxaccount.internal.MenuConfig;
import org.eclipse.kapua.service.wxaccount.internal.MenuConfig.MainConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("WxMenus")
@Path("wechat/menu")
public class WxMenus {
	
	//  private final Logger logger = LoggerFactory.getLogger(this.getClass());
	  
	    @ApiOperation(value = "Get the WxMenu ",  //
	    	    notes = "Returns the  specified by the  path parameter.",  //
	    	    response = String.class)
	    		@POST
	    	    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	    		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public String authGet(
			@ApiParam(value = "The signature of the requested .", required = true) @QueryParam("appId") String appId,	
		    @ApiParam(value = "The timestamp of the requested .", required = true) @QueryParam("appSecret") String appSecret, //
		    @ApiParam(value = "The nonce of the requested ", required = true) @QueryParam("token") String token,
		    @ApiParam(value = "The echostr of the requested ", required = true) @QueryParam("aesKey") String aesKey
	        ){
	    	MainConfig mainConfig = new MainConfig(appId, appSecret, token, aesKey);
			//System.out.println(mainConfig+"mainConfig");
			WxMpService wxMpService = mainConfig.wxMpService();
			String result=null;
			//System.out.println(wxMpService+"wxMpService");
			try {
			   result = wxMpService.getMenuService().menuCreate(MenuConfig.getMenu());
			   System.out.println(wxMpService+"wxMpService1");
			   System.out.println("Result::"+result);
			   System.out.println("toString::"+wxMpService.toString());
		
			} catch (WxErrorException e) {
				e.printStackTrace();
			}

	    	    return result;
	    	  }
	

}
