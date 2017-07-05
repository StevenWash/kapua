/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/

package org.eclipse.kapua.service.appversion.internal;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.AbstractEntityManagerFactory;
import org.eclipse.kapua.commons.jpa.EntityManager;



/**
 * Entity manager factory for the weather module.
 * 
 * @since 1.0
 *
 */
public class AppVersionEntityManagerFactory extends AbstractEntityManagerFactory {

  private static final String PERSISTENCE_UNIT_NAME = "kapua-appVersion";
  private static final String DATASOURCE_NAME = "kapua-dbpool";
  private static final Map<String, String> UNIQUE_CONTRAINTS = new HashMap<>();

  private static AppVersionEntityManagerFactory instance = new AppVersionEntityManagerFactory();

  /**
   * Constructs a new entity manager factory and configure it to use the appVersion persistence unit.
   */
  private AppVersionEntityManagerFactory() {
        super(PERSISTENCE_UNIT_NAME,
                DATASOURCE_NAME,
                UNIQUE_CONTRAINTS);
  }

  /** 
   * Return the {@link EntityManager} singleton instance.
   * 
   * @return not null
   */
  public static AppVersionEntityManagerFactory getInstance() {
    return instance;
  }
    
  /**
   * Return the {@link EntityManager} singleton instance
   * 
   * @return not null
   * @throws KapuaException not null.
   */
  public static EntityManager getEntityManager()
            throws KapuaException {
    return instance.createEntityManager();
  }
}
