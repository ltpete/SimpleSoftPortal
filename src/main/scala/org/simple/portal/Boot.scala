package org.simple.portal

import scala.concurrent.duration.DurationInt
import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http
import org.simple.portal.view.PortalEndpoint
import akka.actor.Actor
import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.prevayler.PrevaylerStorage
 
object Boot extends App {
 
  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[PortalActor], "portal-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "0.0.0.0", port = 80)
  
}

object PersistenceContext extends ActivateContext {
  val storage = new PrevaylerStorage
  override protected def entitiesPackages = List("org.simple.portal.model")
}

class PortalActor extends Actor with PortalEndpoint {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(route)
  
  PersistenceContext.transactional{}
}