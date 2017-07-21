package org.eclipse.kapua.service.replymessage;

import javax.xml.bind.annotation.XmlRegistry;

import org.eclipse.kapua.locator.KapuaLocator;

/**
 * ReplyMessage xml factory class
 * 
 * @since 1.0
 *
 */
@XmlRegistry
public class ReplyMessageXmlRegistry {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
	private final ReplyMessageFactory factory = locator.getFactory(ReplyMessageFactory.class);
	
	 /**
     * Creates a new ReplyMessage instance
     * 
     * @return
     */
	 public ReplyMessage newReplyMessage() {
		 
	
	        return factory.newEntity(null);
	    }
	 
	 
	 
	 /**
	     * Creates a new ReplyMessage creator instance
	     * 
	     * @return
	     */
	    public ReplyMessageCreator newReplyMessageCreator() {
	        return factory.newCreator(null);
	    }

	    /**
	     * Creates a new ReplyMessage list result instance
	     * 
	     * @return
	     */
	    public ReplyMessageListResult newReplyMessageListResult() {
	        return factory.newListResult();
	    }

	    public ReplyMessageQuery newQuery() {
	        return factory.newQuery(null);
	    }
}
