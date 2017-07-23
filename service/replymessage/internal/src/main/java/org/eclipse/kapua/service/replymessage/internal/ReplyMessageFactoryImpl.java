package org.eclipse.kapua.service.replymessage.internal;

import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.replymessage.ReplyMessage;
import org.eclipse.kapua.service.replymessage.ReplyMessageCreator;
import org.eclipse.kapua.service.replymessage.ReplyMessageFactory;
import org.eclipse.kapua.service.replymessage.ReplyMessageListResult;
import org.eclipse.kapua.service.replymessage.ReplyMessageQuery;


@KapuaProvider
public class ReplyMessageFactoryImpl implements ReplyMessageFactory {

  @Override
  public ReplyMessage newEntity(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new ReplyMessageImpl(scopeId);
  }

  @Override
  public ReplyMessageCreator newCreator(KapuaId scopeId) {
    return new ReplyMessageCreatorImpl(scopeId);
  }
  


  @Override
  public ReplyMessageQuery newQuery(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new ReplyMessageQueryImpl(scopeId);
  }

  @Override
  public ReplyMessageListResult newListResult() {
    // TODO Auto-generated method stub
    return new ReplyMessageListResultImpl() ;
  }

 
  

}
