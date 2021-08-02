package com.twitterkata.api.handlers

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.InvalidNicknameException
import com.twitterkata.domain.users.NicknameAlreadyUsedException
import com.twitterkata.domain.users.RegisterUserData
import com.twitterkata.domain.users.actions.RegisterUser
import io.netty.handler.codec.http.HttpResponse
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class RegisterUserHandler(private val registerUser: RegisterUser,
                          private val jsonUtility: JsonUtility) : Handler<RoutingContext> {

    override fun handle(event: RoutingContext) {
        val registerData = jsonUtility.jsonToRegisterData(event.getBodyAsString())
        prepareResponse(registerData, event)
    }

    private fun prepareResponse(
        registerData: RegisterUserData,
        event: RoutingContext
    ) {
        try {
            registerUser.invoke(registerData)
            setResponse(event, HttpResponseStatus.CREATED.code(), jsonUtility.encode(registerData))
        } catch (ex: InvalidNicknameException) {
            setResponse(event, HttpResponseStatus.BAD_REQUEST.code(), "Invalid nickname")
        } catch (ex: NicknameAlreadyUsedException) {
            setResponse(event, HttpResponseStatus.BAD_REQUEST.code(), "Nickname already used")
        }
    }

    private fun setResponse(
        event: RoutingContext,
        statusCode: Int,
        responseString: String
    ) {
        event.response()
            .setStatusCode(statusCode)
            .end(responseString);
    }

}
