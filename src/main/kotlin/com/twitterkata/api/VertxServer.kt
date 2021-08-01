package com.twitterkata.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class VertxServer: AbstractVerticle()  {
    val port = 8888
    val vertxServer = Vertx.vertx()

    override fun start() {
        val router = Router.router(vertx)
        createServer(router)
        router.route().handler(BodyHandler.create())  // Required for RoutingContext.bodyAsString
        TwitterRouter(vertxServer).applyRoutes(router)
    }

    private fun createServer(router: Router) {
        vertxServer.createHttpServer()
            // Handle every request using the router
            .requestHandler(router)
            // Start listening
            .listen(port)
            // Print the port
            .onSuccess { server ->
                println("HTTP server started on port " + server.actualPort())
            }
    }
}