package org.eclipse.kapua.service.wxaccount;

import javax.xml.bind.annotation.XmlRegistry;

import org.eclipse.kapua.locator.KapuaLocator;

/**
 * AppVersion xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class WxAccountXmlRegistry {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
	private final WxAccountFactory factory = locator.getFactory(WxAccountFactory.class);
	
	 /**
     * Creates a new appversion instance
     * 
     * @return
     */
	 public WxAccount newWxAccount() {
		  System.out.println("------------newWxAccount------------");
	
	        return factory.newEntity(null);
	    }
	 
	 
	 
	 /**
	     * Creates a new AppVersion creator instance
	     * 
	     * @return
	     */
	    public WxAccountCreator newWxAccountCreator() {
	        return factory.newCreator(null);
	    }

	    /**
	     * Creates a new AppVersion list result instance
	     * 
	     * @return
	     */
	    public WxAccountListResult newWxAccountListResult() {
	        return factory.newListResult();
	    }

	    public WxAccountQuery newQuery() {
	        return factory.newQuery(null);
	    }
}
