//package com.tiedin.api
//
//import akka.actor.Actor
//import akka.event.slf4j.SLF4JLogging
//import com.tiedin.dao.UserDAO
//import com.tiedin.model._
//import spray.routing._
//
///**
// * REST Service actor.
// */
//class RestServiceActor extends Actor with RestService {
//
//  implicit def actorRefFactory = context
//
//  def receive = runRoute(route)
//}
//
///**
// * REST Service
// */
//trait RestService extends HttpService with SLF4JLogging {
//
//  val userService = UserDAO
//
//  val route =  {
//      path("entity") {
//          get {
//              complete(List(User("foo1", "bar1"), User("foo1", "bar1")))
//          }
//      }
//  }
//    //    path("users") {
//    //      post {
//    //        entity(Unmarshaller(MediaTypes.`application/json`) {
//    //          case httpEntity: HttpEntity =>
//    //            read[User](httpEntity.asString(HttpCharsets.`UTF-8`))
//    //        }) {
//    //          user: User =>
//    //            ctx: RequestContext =>
//    //              handleRequest(ctx, StatusCodes.Created) {
//    //                log.debug("Creating customer: %s".format(user))
//    //                userService.create(user)
//    //              }
//    //        }
//    //      }
//    ////      ~
//    ////        get {
//    ////          parameters('firstName.as[String] ?, 'lastName.as[String] ?, 'birthday.as[Date] ?).as(CustomerSearchParameters) {
//    ////            searchParameters: CustomerSearchParameters => {
//    ////              ctx: RequestContext =>
//    ////                handleRequest(ctx) {
//    ////                  log.debug("Searching for customers with parameters: %s".format(searchParameters))
//    ////                  userService.search(searchParameters)
//    ////                }
//    ////            }
//    ////          }
//    ////        }
//    //    } ~
////    path("users" / LongNumber) {
////      userId =>
////        put {
////          //            entity(Unmarshaller(MediaTypes.`application/json`) {
////          //              case httpEntity: HttpEntity =>
////          //                read[User](httpEntity.asString(HttpCharsets.`UTF-8`))
////          //            }) {
////          //              user: User =>
////          //                ctx: RequestContext =>
////          //                  handleRequest(ctx) {
////          //                    log.debug("Updating user with id %d: %s".format(userId, user))
////          //                    userService.update(userId, user)
////          //                  }
////          //            }
////          //          } ~
////          delete {
////            ctx: RequestContext =>
////              handleRequest(ctx) {
////                log.debug("Deleting user with id %d".format(userId))
////                userService.delete(userId)
////              }
////          } ~
////              get {
////                ctx: RequestContext =>
////                  handleRequest(ctx) {
////                    log.debug("Retrieving user with id %d".format(userId))
////                    userService.get(userId)
////                  }
////              }
////        }
////    }
////  }
//
////  /**
////   * Handles an incoming request and create valid response for it.
////   *
////   * @param ctx         request context
////   * @param successCode HTTP Status code for success
////   * @param action      action to perform
////   */
////  protected def handleRequest(ctx: RequestContext, successCode: StatusCode = StatusCodes.OK)(action: => Either[Failure, _]) {
////    action match {
////      case Right(result: Object) =>
////        ctx.complete(successCode, write(result))
////      case Left(error: Failure) =>
////        ctx.complete(error.getStatusCode, net.liftweb.json.Serialization.write(Map("error" -> error.message)))
////      case _ =>
////        ctx.complete(StatusCodes.InternalServerError)
////    }
////  }
//}