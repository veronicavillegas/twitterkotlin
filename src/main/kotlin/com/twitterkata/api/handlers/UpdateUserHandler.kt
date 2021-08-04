package com.twitterkata.api.handlers

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.requestData.UpdateUserData
import com.twitterkata.domain.users.exceptions.InexistentUserException
import com.twitterkata.domain.users.actions.UpdateUser
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class UpdateUserHandler(private val updateUser: UpdateUser,
                        private val jsonUtility: JsonUtility) : Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val updateUserData = jsonUtility.jsonToUpdateUserData(event.getBodyAsString("utf-8"))
        val nickname = event.pathParam("nickname")
        prepareResponse(nickname, updateUserData, event)
    }

    private fun prepareResponse(
        nickname: String,
        updateUserData: UpdateUserData,
        event: RoutingContext
    ) {
        try {
            updateUser.invoke(nickname, updateUserData)
            setResponse(event, HttpResponseStatus.OK.code(), "User updated")
        } catch (ex: InexistentUserException) {
            setResponse(event, HttpResponseStatus.BAD_REQUEST.code(), "Inexistent user")
        }
    }

    private fun setResponse(event: RoutingContext, code: Int, end: String) {
        event.response().statusCode = code
        event.response().end(end)
    }
}
