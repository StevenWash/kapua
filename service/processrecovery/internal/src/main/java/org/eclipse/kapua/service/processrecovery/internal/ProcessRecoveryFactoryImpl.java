package org.eclipse.kapua.service.processrecovery.internal;

import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryCreator;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryFactory;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryListResult;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryQuery;

@KapuaProvider
public class ProcessRecoveryFactoryImpl implements ProcessRecoveryFactory {

  @Override
  public ProcessRecovery newEntity(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new ProcessRecoveryImpl(scopeId);
  }

  @Override
  public ProcessRecoveryCreator newCreator(KapuaId scopeId) {
    return new ProcessRecoveryCreatorImpl(scopeId);
  }
  


  @Override
  public ProcessRecoveryQuery newQuery(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new ProcessRecoveryQueryImpl(scopeId);
  }

  @Override
  public ProcessRecoveryListResult newListResult() {
    // TODO Auto-generated method stub
    return new ProcessRecoveryListResultImpl() ;
  }

 
  

}
