package org.eclipse.kapua.service.wxaccount.internal;

import org.eclipse.kapua.commons.model.query.predicate.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.wxaccount.WxAccount;
import org.eclipse.kapua.service.wxaccount.WxAccountQuery;

public class WxAccountQueryImpl extends AbstractKapuaQuery<WxAccount> implements WxAccountQuery {
  
  private WxAccountQueryImpl() {
    super();
  }

  /**
  * Constructor.
  *
  * @param scopeId not null
  */
  public WxAccountQueryImpl(KapuaId scopeId) {
    this();
    setScopeId(scopeId);
  }

}
