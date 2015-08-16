package org.simple.portal.service

import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.routing.directives.FileAndResourceDirectives

trait PortalService extends HttpService with FileAndResourceDirectives {

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
    path("") {
      getFromResource("web/index.html")
    } ~
    pathPrefix("") {
      getFromResourceDirectory("web")
    }

}