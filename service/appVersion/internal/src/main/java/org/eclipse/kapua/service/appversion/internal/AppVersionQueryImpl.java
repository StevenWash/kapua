package org.eclipse.kapua.service.appversion.internal;

import org.eclipse.kapua.commons.model.query.predicate.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionQuery;

public class AppVersionQueryImpl extends AbstractKapuaQuery<AppVersion> implements AppVersionQuery {
  
  private AppVersionQueryImpl() {
    super();
  }

  /**
  * Constructor.
  *
  * @param scopeId not null
  */
  public AppVersionQueryImpl(KapuaId scopeId) {
    this();
    setScopeId(scopeId);
  }

}
