package org.eclipse.kapua.service.wxaccount.internal;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

public class MenuConfig {
	
	
	public static WxMenu getMenu() {

		MainConfig mainConfig = new MainConfig("appid", "appsecret", "token", "aesKey");

        WxMpService wxMpService = mainConfig.wxMpService();

		WxMenu menu = new WxMenu();
		WxMenuButton button1 = new WxMenuButton();
		button1.setType(WxConsts.BUTTON_CLICK);
		button1.setName("用户注册");
		button1.setKey("hai");

		WxMenuButton button2 = new WxMenuButton();
		button2.setName("信息管理");

		/*WxMenuButton button21 = new WxMenuButton();
		button21.setType(WxConsts.BUTTON_VIEW);
		button21.setName("我的订单");
		button21.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));*/

		WxMenuButton button22 = new WxMenuButton();
		button22.setType(WxConsts.BUTTON_VIEW);
		button22.setName("搜狐");
		button22.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));

		/*WxMenuButton button23 = new WxMenuButton();
		button23.setType(WxConsts.BUTTON_VIEW);
		button23.setName("发布商品");
		button23.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));*/

		/*WxMenuButton button24 = new WxMenuButton();
		button24.setType(WxConsts.BUTTON_VIEW);
		button24.setName("商品管理");
		button24.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));*/

	  //button2.getSubButtons().add(button21);
		button2.getSubButtons().add(button22);
	//	button2.getSubButtons().add(button23);
	//	button2.getSubButtons().add(button24);

		WxMenuButton button3 = new WxMenuButton();
		button3.setType(WxConsts.BUTTON_CLICK);
		button3.setName("使用帮助");
		button3.setKey("help");

		menu.getButtons().add(button1);
		menu.getButtons().add(button2);
		menu.getButtons().add(button3);

		return menu;
	}


	public static class MainConfig {

		private String appId;
		private String appSecret;
		private String token;
		private String aesKey;

		/**
		 * 为了生成自定义菜单使用的构造函数
		 *
		 * @param appId
		 * @param appSecret
		 * @param token
		 * @param aesKey
		 */
		public MainConfig(String appId, String appSecret, String token, String aesKey) {
			this.appId = appId;
			this.appSecret = appSecret;
			this.token = token;
			this.aesKey = aesKey;
		}

		public WxMpConfigStorage wxMpConfigStorage() {
			WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
			configStorage.setAppId(this.appId);
			configStorage.setSecret(this.appSecret);
			configStorage.setToken(this.token);
			configStorage.setAesKey(this.aesKey);
			return configStorage;
		}

		public WxMpService wxMpService() {
			WxMpService wxMpService = new WxMpServiceImpl();
			wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
			return wxMpService;
		}


    }
}
