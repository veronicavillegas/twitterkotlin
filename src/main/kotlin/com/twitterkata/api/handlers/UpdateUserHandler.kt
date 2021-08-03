package com.twitterkata.api.handlers

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.actions.UpdateUser
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class UpdateUserHandler(private val updateUser: UpdateUser,
                        private val jsonUtility: JsonUtility) : Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val updateUserData = jsonUtility.jsonToUpdateUserData(event.getBodyAsString(""))
        val nickname = event.request().getParam("nickname")
        try{
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
