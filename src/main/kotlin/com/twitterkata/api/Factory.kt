package com.twitterkata.api

import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser

interface Factory {
    fun getJsonUtility(): JsonMapper
    fun getRegisterUserAction(): RegisterUser
    fun getUpdateUserAction(): UpdateUser
    fun initDatabaseConnection()
}