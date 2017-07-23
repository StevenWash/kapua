package org.eclipse.kapua.service.processrecovery;

import javax.xml.bind.annotation.XmlRegistry;

import org.eclipse.kapua.locator.KapuaLocator;

/**
 * ReplyMessage xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class ProcessRecoveryXmlRegistry {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
	private final ProcessRecoveryFactory factory = locator.getFactory(ProcessRecoveryFactory.class);
	
	 /**
     * Creates a new ProcessRecovery instance
     * 
     * @return
     */
	 public ProcessRecovery newProcessRecovery() {
		 
	
	        return factory.newEntity(null);
	    }
	 
	 
	 
	 /**
	     * Creates a new ProcessRecovery creator instance
	     * 
	     * @return
	     */
	    public ProcessRecoveryCreator newProcessRecoveryCreator() {
	        return factory.newCreator(null);
	    }

	    /**
	     * Creates a new ProcessRecovery list result instance
	     * 
	     * @return
	     */
	    public ProcessRecoveryListResult newProcessRecoveryListResult() {
	        return factory.newListResult();
	    }

	    public ProcessRecoveryQuery newQuery() {
	        return factory.newQuery(null);
	    }
}
