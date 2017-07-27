package org.eclipse.kapua.service.wxaccount;

import org.eclipse.kapua.model.KapuaEntityFactory;
import org.eclipse.kapua.model.id.KapuaId;


public interface WxAccountFactory extends KapuaEntityFactory<WxAccount, WxAccountCreator, WxAccountQuery, WxAccountListResult> {

	  /**
     * Creates a new {@link WxAccountCreator} for the specified name
     * 
     * @param scopeId
     * @param name
     * @return
     */
    public WxAccountCreator newCreator(KapuaId scopeId, String name);
	 
	 
}
