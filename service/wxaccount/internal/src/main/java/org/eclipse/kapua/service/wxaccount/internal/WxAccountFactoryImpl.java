package org.eclipse.kapua.service.wxaccount.internal;

import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.wxaccount.WxAccount;
import org.eclipse.kapua.service.wxaccount.WxAccountCreator;
import org.eclipse.kapua.service.wxaccount.WxAccountFactory;
import org.eclipse.kapua.service.wxaccount.WxAccountListResult;
import org.eclipse.kapua.service.wxaccount.WxAccountQuery;

@KapuaProvider
public class WxAccountFactoryImpl implements WxAccountFactory {

  @Override
  public WxAccount newEntity(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new WxAccountImpl(scopeId);
  }

  @Override
  public WxAccountCreator newCreator(KapuaId scopeId) {
    return new WxAccountCreatorImpl(scopeId);
  }
  


  @Override
  public WxAccountQuery newQuery(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new WxAccountQueryImpl(scopeId);
  }

  @Override
  public WxAccountListResult newListResult() {
    // TODO Auto-generated method stub
    return new WxAccountListResultImpl() ;
  }

 
  

}
