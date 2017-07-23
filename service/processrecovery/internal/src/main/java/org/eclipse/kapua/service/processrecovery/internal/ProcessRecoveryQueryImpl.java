package org.eclipse.kapua.service.processrecovery.internal;

import org.eclipse.kapua.commons.model.query.predicate.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryQuery;

public class ProcessRecoveryQueryImpl extends AbstractKapuaQuery<ProcessRecovery> 
    implements ProcessRecoveryQuery {
  
  private ProcessRecoveryQueryImpl() {
    super();
  }

  /**
  * Constructor.
  *
  * @param scopeId not null
  */
  public ProcessRecoveryQueryImpl(KapuaId scopeId) {
    this();
    setScopeId(scopeId);
  }

}
