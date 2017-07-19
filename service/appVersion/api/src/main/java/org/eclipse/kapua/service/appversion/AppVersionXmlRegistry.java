package org.eclipse.kapua.service.appversion;

import javax.xml.bind.annotation.XmlRegistry;

import org.eclipse.kapua.locator.KapuaLocator;

/**
 * AppVersion xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class AppVersionXmlRegistry {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
	private final AppVersionFactory factory = locator.getFactory(AppVersionFactory.class);
	
	 /**
     * Creates a new appversion instance
     * 
     * @return
     */
	 public AppVersion newAppVersion() {
		    
	        return factory.newEntity(null);
	    }
	 
	 
	 
	 /**
	     * Creates a new AppVersion creator instance
	     * 
	     * @return
	     */
	    public AppVersionCreator newAppVersionCreator() {
	        return factory.newCreator(null);
	    }

	    /**
	     * Creates a new AppVersion list result instance
	     * 
	     * @return
	     */
	    public AppVersionListResult newAppVersionListResult() {
	        return factory.newListResult();
	    }

	    public AppVersionQuery newQuery() {
	        return factory.newQuery(null);
	    }
}
