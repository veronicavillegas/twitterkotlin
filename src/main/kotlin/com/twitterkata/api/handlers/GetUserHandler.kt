package com.twitterkata.api.handlers

import com.twitterkata.api.JsonMapper
import com.twitterkata.domain.users.actions.GetUser
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class GetUserHandler(private val getUser: GetUser, private val jsonMapper: JsonMapper) : Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val user = getUser(event.pathParam("nickname"))
        if(user == null) {
            event.response().end("Inexistent user")
        } else {
            event.response().end(jsonMapper.encode(user))
        }
    }

}
