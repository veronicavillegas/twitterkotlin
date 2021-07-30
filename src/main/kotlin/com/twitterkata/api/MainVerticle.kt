package com.twitterkata.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class MainVerticle: AbstractVerticle()  {
    override fun start() {
        val vertx = Vertx.vertx()
        val router = Router.router(vertx)
        Routes(router)
        createServer(vertx, router)
    }

    private fun createServer(vertx: Vertx, router: Router?) {
        vertx.createHttpServer()
            // Handle every request using the router
            .requestHandler(router)
            // Start listening
            .listen(8888)
            // Print the port
            .onSuccess { server ->
                println("HTTP server started on port " + server.actualPort())
            }
    }
}