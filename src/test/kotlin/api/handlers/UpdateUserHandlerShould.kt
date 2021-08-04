package api.handlers

import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.verify
import com.twitterkata.api.handlers.UpdateUserHandler
import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.requestData.UpdateUserData
import com.twitterkata.domain.users.exceptions.InexistentUserException
import com.twitterkata.domain.users.actions.UpdateUser
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UpdateUserHandlerShould {
    val event = mock(RoutingContext::class.java)
    val updateUser = mock(UpdateUser::class.java)
    val jsonUtility = mock(JsonUtility::class.java)

    val updateUserHandler = UpdateUserHandler(updateUser, jsonUtility)
    val request = mock(HttpServerRequest::class.java)
    val response = mock(HttpServerResponse::class.java)
    val updateUserData = UpdateUserData("veronica", "villegas")
    val body = Gson().toJson(updateUserData)
    val nickname = "@vero"

    @Test
    fun `update user when data is correct`() {
        givenUpdateUserRequest()
        givenMockResponse()
        givenJsonUtility()
        givenUpdateUserAction()

        whenUpdateUser()

        thenUserSucessfullyUpdated()

    }

    @Test
    fun throwExceptionWhenUserNotExists() {
        givenUpdateUserRequest()
        givenMockResponse()
        givenJsonUtility()
        givenUpdateUserActionWithInexistentUserError()

        whenUpdateUser()

        thenInexistenUserIsReturned()
    }


    private fun thenUserSucessfullyUpdated() {
        verify(updateUser).invoke(nickname, updateUserData)
        verify(response).setStatusCode(200)
        verify(response).end("User updated")
    }

    private fun thenInexistenUserIsReturned() {
        verify(updateUser).invoke(nickname, updateUserData)
        verify(response).setStatusCode(400)
        verify(response).end("Inexistent user")
    }

    private fun whenUpdateUser() {
        updateUserHandler.handle(event)
    }

    private fun givenUpdateUserAction() {
        Mockito.doNothing().`when`(updateUser).invoke(nickname, updateUserData)
    }

    private fun givenMockResponse() {
        Mockito.`when`(event.response()).thenReturn(response)
    }

    private fun givenUpdateUserRequest() {
        Mockito.`when`(event.getBodyAsString("utf-8")).thenReturn(body)
        Mockito.`when`(event.pathParam("nickname")).thenReturn(nickname)
        Mockito.`when`(event.request()).thenReturn(request)
        Mockito.`when`(event.request().getParam("nickname")).thenReturn(nickname)
    }

    private fun givenUpdateUserActionWithInexistentUserError() {
        Mockito.`when`(updateUser.invoke(nickname, updateUserData)).thenThrow(InexistentUserException())
    }

    private fun givenJsonUtility() {
        Mockito.`when`(jsonUtility.jsonToUpdateUserData(body)).thenReturn(updateUserData)
    }

}