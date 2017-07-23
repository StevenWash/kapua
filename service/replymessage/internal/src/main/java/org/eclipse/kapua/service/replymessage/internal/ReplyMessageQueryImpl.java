package org.eclipse.kapua.service.replymessage.internal;

import org.eclipse.kapua.commons.model.query.predicate.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.replymessage.ReplyMessage;
import org.eclipse.kapua.service.replymessage.ReplyMessageQuery;


public class ReplyMessageQueryImpl extends AbstractKapuaQuery<ReplyMessage> 
    implements ReplyMessageQuery {
  
  private ReplyMessageQueryImpl() {
    super();
  }

  /**
  * Constructor.
  *
  * @param scopeId not null
  */
  public ReplyMessageQueryImpl(KapuaId scopeId) {
    this();
    setScopeId(scopeId);
  }

}
