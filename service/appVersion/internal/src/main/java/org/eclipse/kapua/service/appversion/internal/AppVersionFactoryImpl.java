package org.eclipse.kapua.service.appversion.internal;

import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;
import org.eclipse.kapua.service.appversion.AppVersionFactory;
import org.eclipse.kapua.service.appversion.AppVersionListResult;
import org.eclipse.kapua.service.appversion.AppVersionQuery;

@KapuaProvider
public class AppVersionFactoryImpl implements AppVersionFactory {

  @Override
  public AppVersion newEntity(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new AppVersionImpl(scopeId);
  }

  @Override
  public AppVersionCreator newCreator(KapuaId scopeId) {
    return new AppVersionCreatorImpl(scopeId);
  }
  


  @Override
  public AppVersionQuery newQuery(KapuaId scopeId) {
    // TODO Auto-generated method stub
    return new AppVersionQueryImpl(scopeId);
  }

  @Override
  public AppVersionListResult newListResult() {
    // TODO Auto-generated method stub
    return new AppVersionListResultImpl() ;
  }

 
  

}
