package com.twitterkata.api.handlers

import com.twitterkata.api.JsonMapper
import com.twitterkata.domain.followers.actions.FollowUser
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class FollowUserHandler(private val followUser: FollowUser, private val jsonMapper: JsonMapper) : Handler<RoutingContext> {
    override fun handle(event: RoutingContext?) {
        TODO("Not yet implemented")
    }

}
