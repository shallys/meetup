package com.ibm.meetup.interactive

import org.http4s.server.blaze.BlazeBuilder

object BlazeExample extends App {
  BlazeBuilder.bindHttp(8080)
    .mountService(KernelAPI.service, "/")
    .run
    .awaitShutdown()
}
