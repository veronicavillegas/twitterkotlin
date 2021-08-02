package api.handlers

import com.nhaarman.mockitokotlin2.any
import com.twitterkata.api.handlers.RegisterUserHandler
import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.InvalidNicknameException
import com.twitterkata.domain.users.NicknameAlreadyUsedException
import com.twitterkata.domain.users.RegisterUserData
import com.twitterkata.domain.users.actions.RegisterUser
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.jvm.Throws
import kotlin.test.Ignore
import kotlin.test.assertEquals


class RegisterUserHandlerShould {
    val requestBody = "{\n\t\"nickname\": \"@vero\", \n\t\"surname\": \"villegas\", \n\t\"firstname\": \"vero\"\n}"
    val registerData = RegisterUserData("vero", "villegas","@vero")

    val registerUser = mock(RegisterUser::class.java)
    val event = mock(RoutingContext::class.java)
    val jsonUtility = mock(JsonUtility::class.java)
    val response = mock(HttpServerResponse::class.java)
    val registerUserHandler = RegisterUserHandler(registerUser, jsonUtility)
    
    @Ignore
    @Test
    fun saveUserWhenRegisterDataIsGiven() {
        givenRegisterData()
        Mockito.doNothing().`when`(registerUser).invoke(registerData)
        whenRegisterUser()
        assertEquals(200,  response.statusCode)
    }

    @Test
    fun throwErrorIfNicknameAlreadyExists() {
        givenRegisterData()
        givenNicknameAlreadyUsedException()

        whenRegisterUser()

        assertEquals(400, response.statusCode)
    }

    private fun givenNicknameAlreadyUsedException() {
        Mockito.`when`(registerUser.invoke(registerData)).thenThrow(NicknameAlreadyUsedException())
    }

    @Test
    fun throwErrorIfNicknameIsNotValid() {
        givenRegisterData()
        givenInvalidNicknameException()

        whenRegisterUser()

        assertEquals(400, response.statusCode)
    }

    private fun givenInvalidNicknameException() {
        Mockito.`when`(registerUser.invoke(registerData)).thenThrow(InvalidNicknameException())
    }

    private fun whenRegisterUser() {
        registerUserHandler.handle(event)
    }

    private fun givenRegisterData() {
        Mockito.`when`(event.getBodyAsString()).thenReturn(requestBody)
        Mockito.`when`(event.response()).thenReturn(response)
        Mockito.`when`(jsonUtility.jsonToRegisterData(requestBody)).thenReturn(registerData)
        Mockito.`when`(jsonUtility.encode(registerData)).thenReturn(requestBody)
        Mockito.`when`(response.setStatusCode(any())).thenReturn(response)
    }

}