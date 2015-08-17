package org.simple.portal.service

import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.routing.directives.FileAndResourceDirectives
import com.typesafe.scalalogging.LazyLogging


trait PortalService extends HttpService with FileAndResourceDirectives with LazyLogging {

  
  val route =
    path("valami") {
      get {
        complete {
          <html>
            <body>
              <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
            </body>
          </html>
        }
      }
    } ~
    pathPrefix("rest") { 
      path("employees") {
        get {
          complete {
            logger.debug("a request has arrived")
            """{"employees":[
                {"firstName":"John", "lastName":"Doe"},
                {"firstName":"Anna", "lastName":"Smith"},
                {"firstName":"Peter", "lastName":"Jones"}
            ]}"""
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