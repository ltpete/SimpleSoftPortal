package org.simple.portal

import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.prevayler.PrevaylerStorage

package object model {

  implicit object PersistenceContext extends ActivateContext {
    val storage = new PrevaylerStorage
    override protected def entitiesPackages = List("org.simple.portal.model")
  }

}