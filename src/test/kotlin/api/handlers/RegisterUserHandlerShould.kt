package api.handlers

import com.twitterkata.api.handlers.RegisterUserHandler
import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.RegisterUserData
import com.twitterkata.domain.users.actions.RegisterUser
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
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
    fun whenRegisterDataIsGiven_ThenAUserShouldBeSaved() {
        given()
        whenRegisterUser()
        assertEquals(200,  response.statusCode)
    }

    private fun whenRegisterUser() {
        registerUserHandler.handle(event)
    }

    private fun given() {
        Mockito.`when`(event.getBodyAsString()).thenReturn(requestBody)
        Mockito.`when`(event.response()).thenReturn(response)
        Mockito.`when`(jsonUtility.jsonToRegisterData(requestBody)).thenReturn(registerData)
        Mockito.`when`(jsonUtility.encode(registerData)).thenReturn(requestBody)
        Mockito.doNothing().`when`(registerUser).invoke(registerData)
        Mockito.`when`(response.setStatusCode(201)).thenReturn(response)
    }

}