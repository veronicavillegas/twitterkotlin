package com.twitterkata.api

import com.twitterkata.domain.users.requestData.RegisterUserData
import com.twitterkata.domain.users.requestData.UpdateUserData

interface JsonMapper {
    fun jsonToRegisterData(bodyAsString: String): RegisterUserData
    fun encode(objectToEncode: Any): String
    fun jsonToUpdateUserData(bodyAsString: String?): UpdateUserData
}
