package com.twitterkata

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser

interface Factory {
    fun getJsonUtility(): JsonUtility
    fun getRegisterUserAction(): RegisterUser
    fun getUpdateUserAction(): UpdateUser
    fun initDatabaseConnection()
}