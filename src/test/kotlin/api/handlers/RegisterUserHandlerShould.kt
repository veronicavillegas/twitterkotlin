package api.handlers

import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.verify
import com.twitterkata.api.handlers.RegisterUserHandler
import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.exceptions.InvalidNicknameException
import com.twitterkata.domain.users.exceptions.NicknameAlreadyUsedException
import com.twitterkata.domain.users.requestData.RegisterUserData
import com.twitterkata.domain.users.actions.RegisterUser
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


class RegisterUserHandlerShould {
    val registerData = RegisterUserData("vero", "villegas","@vero")
    val requestBody = Gson().toJson(registerData)

    val registerUser = mock(RegisterUser::class.java)
    val event = mock(RoutingContext::class.java)
    val jsonUtility = mock(JsonUtility::class.java)
    val response = mock(HttpServerResponse::class.java)

    val registerUserHandler = RegisterUserHandler(registerUser, jsonUtility)

    @Test
    fun saveUserWhenRegisterDataIsGiven() {
        givenRegisterData()
        Mockito.doNothing().`when`(registerUser).invoke(registerData)
        whenRegisterUser()
        verifyCreatedUserIsReturned()
    }

    @Test
    fun throwErrorIfNicknameAlreadyExists() {
        givenRegisterData()
        givenNicknameAlreadyUsedException()
        whenRegisterUser()
        verifyExistentNicknameIsReturned()
    }

    @Test
    fun throwErrorIfNicknameIsNotValid() {
        givenRegisterData()
        givenInvalidNicknameException()
        whenRegisterUser()
        verifyInvalidNicknameIsReturned()
    }

    private fun verifyCreatedUserIsReturned() {
        verify(response).statusCode = 201
        verify(response).end(requestBody)
    }



    private fun verifyExistentNicknameIsReturned() {
        verify(response).setStatusCode(400)
        verify(response).end("Nickname already used")
    }

    private fun verifyInvalidNicknameIsReturned() {
        verify(response).setStatusCode(400)
        verify(response).end("Invalid nickname")
    }

    private fun givenNicknameAlreadyUsedException() {
        Mockito.`when`(registerUser.invoke(registerData)).thenThrow(NicknameAlreadyUsedException())
    }

    private fun givenInvalidNicknameException() {
        Mockito.`when`(registerUser.invoke(registerData)).thenThrow(InvalidNicknameException())
    }

    private fun whenRegisterUser() {
        registerUserHandler.handle(event)
    }

    private fun givenRegisterData() {
        Mockito.`when`(event.getBodyAsString("")).thenReturn(requestBody)
        Mockito.`when`(event.response()).thenReturn(response)
        Mockito.`when`(jsonUtility.jsonToRegisterData(requestBody)).thenReturn(registerData)
        Mockito.`when`(jsonUtility.encode(registerData)).thenReturn(requestBody)
    }

}