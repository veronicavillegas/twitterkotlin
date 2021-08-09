package com.twitterkata.api

import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.users.actions.GetUser
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser

interface Factory {
    fun getJsonMapper(): JsonMapper
    fun getRegisterUserAction(): RegisterUser
    fun getUpdateUserAction(): UpdateUser
    fun initDatabaseConnection()
    fun getUserAction(): GetUser
    fun getFollowUserAction(): FollowUser
}