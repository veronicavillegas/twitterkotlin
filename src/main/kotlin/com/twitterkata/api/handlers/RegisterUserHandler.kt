package com.twitterkata.api.handlers

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.actions.RegisterUser
import io.netty.handler.codec.http.HttpResponse
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class RegisterUserHandler(private val registerUser: RegisterUser,
                          private val jsonUtility: JsonUtility) : Handler<RoutingContext> {

    override fun handle(event: RoutingContext) {
        val registerData = jsonUtility.jsonToRegisterData(event.getBodyAsString())

        registerUser.invoke(registerData)

        event.response()
            .setStatusCode(HttpResponseStatus.CREATED.code())
            .end(jsonUtility.encode(registerData));
    }

}
