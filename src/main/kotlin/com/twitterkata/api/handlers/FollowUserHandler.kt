package com.twitterkata.api.handlers

import com.twitterkata.api.JsonMapper
import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.users.exceptions.InexistentUserException
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class FollowUserHandler(private val followUser: FollowUser, private val jsonMapper: JsonMapper) : Handler<RoutingContext> {
    override fun handle(event: RoutingContext?) {
        event?.runCatching { -> event
            val userNickname = this.pathParam("nickname")
            val userNicknameToFollow = this.pathParam("nicknameToFollow")
            followUser.invoke(userNickname, userNicknameToFollow)
            event.response().end("Now $userNickname follows to $userNicknameToFollow")
        }?.onFailure {
            when (it) {
                is InexistentUserException -> {
                    event.response().end("One or both users not exists")
                }
            }
        }
    }

}
