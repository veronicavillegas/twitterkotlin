package com.twitterkata.api.handlers

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class HelloHandler : Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        event.response().end("Hello! KataTwitter")
    }

}
