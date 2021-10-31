package com.twitterkata.api.handlers

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.verify
import com.twitterkata.api.JsonMapper
import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.users.exceptions.InexistentUserException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FollowUserHandlerShould{
    private val jsonMapper : JsonMapper = mockk()
    private val followUser : FollowUser = mockk(relaxed = true)
    private val followUserHandler = FollowUserHandler(followUser, jsonMapper)
    private val event : RoutingContext = mockk()
    private val response : HttpServerResponse = mockk(relaxed = true)

    @Test
    fun `invoke to followUser action`() {
        every { event.pathParam("nickname") } returns "juan"
        every { event.pathParam("nicknameToFollow") } returns "maria"
        every { event.response() } returns response

        followUserHandler.handle(event)

        verify() { followUser.invoke(any(), any())}
        verify() { event.response()}
        verify() { response.end("Now juan follows to maria")}
    }

    @Test
    internal fun `notice one or both users not exists`() {
        every { event.pathParam("nickname") } returns "juan"
        every { event.pathParam("nicknameToFollow") } returns "maria"
        every { event.response() } returns response
        every { followUser.invoke(any(), any()) } throws InexistentUserException()

        followUserHandler.handle(event)

        verify() { followUser.invoke(any(), any())}
        verify() { event.response()}
        verify() { response.end("One or both users not exists")}
    }
}