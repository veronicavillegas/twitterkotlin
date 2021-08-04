package com.twitterkata.domain

import com.twitterkata.domain.users.requestData.RegisterUserData
import com.twitterkata.domain.users.requestData.UpdateUserData

interface JsonUtility {
    fun jsonToRegisterData(bodyAsString: String): RegisterUserData
    fun encode(objectToEncode: Any): String
    fun jsonToUpdateUserData(bodyAsString: String?): UpdateUserData
}
