package com.twitterkata.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class VertxServer(private val factory: Factory): AbstractVerticle()  {
    val port = 8888
    val vertxServer = Vertx.vertx()

    override fun start() {
        val router = Router.router(vertx)
        createServer(router)
        router.route().handler(BodyHandler.create())
        TwitterRouter(vertxServer, factory).applyRoutes(router)
    }

    private fun createServer(router: Router) {
        vertxServer.createHttpServer()
            .requestHandler(router)
            .listen(port)
            .onSuccess { server ->
                println("HTTP server started on port " + server.actualPort())
            }
    }
}