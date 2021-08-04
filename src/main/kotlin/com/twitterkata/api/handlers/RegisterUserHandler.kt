package com.twitterkata.api.handlers

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.exceptions.InvalidNicknameException
import com.twitterkata.domain.users.exceptions.NicknameAlreadyUsedException
import com.twitterkata.domain.users.requestData.RegisterUserData
import com.twitterkata.domain.users.actions.RegisterUser
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class RegisterUserHandler(private val registerUser: RegisterUser,
                          private val jsonUtility: JsonUtility) : Handler<RoutingContext> {

    override fun handle(event: RoutingContext) {
        val registerData = jsonUtility.jsonToRegisterData(event.getBodyAsString("utf-8"))
        prepareResponse(registerData, event)
    }

    private fun prepareResponse(
        registerData: RegisterUserData,
        event: RoutingContext
    ) {
        try {
            registerUser.invoke(registerData)
            event.setResponse(HttpResponseStatus.CREATED.code(), jsonUtility.encode(registerData))
        } catch (ex: InvalidNicknameException) {
            event.setResponse(HttpResponseStatus.BAD_REQUEST.code(), "Invalid nickname")
        } catch (ex: NicknameAlreadyUsedException) {
            event.setResponse(HttpResponseStatus.BAD_REQUEST.code(), "Nickname already used")
        }
    }

    private fun RoutingContext.setResponse(
        statusCode: Int,
        responseString: String
    ) {
        response().statusCode = statusCode
        response().end(responseString)
    }

}
