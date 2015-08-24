package org.simple.portal.view

import spray.routing.HttpService
import spray.routing.directives.FileAndResourceDirectives
import com.typesafe.scalalogging.LazyLogging
import org.simple.portal.model._
import org.simple.portal.PersistenceContext._
import spray.http.MediaTypes
import spray.httpx.SprayJsonSupport._
import spray.json._
import net.fwbrasil.activate.json.spray.SprayJsonContext

object portalContext extends SprayJsonContext {
  val context = org.simple.portal.PersistenceContext
}

object PortalProtocol extends DefaultJsonProtocol {
  implicit object PersonFormat extends RootJsonFormat[Person] {
    def read(json: JsValue): Person = transactional {
      portalContext.createOrUpdateEntityFromJson[Person](json.asJsObject)
    }

    def write(obj: Person): JsValue = {
      portalContext.createJsonFromEntity(obj, 1, List.empty[String], List.empty[String])
    }
  }
}

import PortalProtocol._

trait PortalEndpoint extends HttpService with FileAndResourceDirectives with LazyLogging {

  val route =
    pathPrefix("rest") {
      respondWithMediaType(MediaTypes.`application/json`) {
        path("employees") {
          get {
            complete {
              logger.debug("a GET request has arrived")
              transactional {
                all[Person]
              }
            }
          } ~
            (post | put) {
              entity(as[Person]) { person =>
                complete {
                  logger.debug(s"a PUT request has arrived $person")
                  transactional {
                    person
                  }
                }
              }
            }
        } ~
        path("employees" / ".*".r ) { id =>
          delete { 
            complete {
              logger.debug(s"a DELETE request has arrived $id")
              transactional {
                val result = query { (person: Person) => where(person.id :== id) select (person) }
                for (person <- result) {
                  person.delete
                  println("Deleted: " + person)
                }
              }
              ""
            }
          }
        }
      }
    } ~
      path("") {
        getFromResource("web/index.html")
      } ~
      pathPrefix("") {
        getFromResourceDirectory("web")
      }

}