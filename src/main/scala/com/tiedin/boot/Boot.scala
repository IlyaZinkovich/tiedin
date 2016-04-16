package com.tiedin.boot

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.tiedin.api.RestServiceActor
import com.tiedin.config.Configuration
import spray.can.Http

object Boot extends App with Configuration {

  // create an actor system for application
  implicit val system = ActorSystem("rest-service-example")

  // create and start rest service actor
  val restService = system.actorOf(Props[RestServiceActor], "rest-endpoint")

  // start HTTP server with rest service actor as a handler
  IO(Http) ! Http.Bind(restService, serviceHost, servicePort)
}