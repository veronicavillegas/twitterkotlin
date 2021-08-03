package com.twitterkata.api.handlers

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.UpdateUserData
import com.twitterkata.domain.users.actions.UpdateUser
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class UpdateUserHandler(private val updateUser: UpdateUser,
                        private val jsonUtility: JsonUtility) : Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val updateUserData = jsonUtility.jsonToUpdateUserData(event.getBodyAsString(""))
        val nickname = event.request().getParam("nickname")
        updateUser.invoke(nickname, updateUserData)
        event.response().setStatusCode(HttpResponseStatus.OK.code())
    }

}
