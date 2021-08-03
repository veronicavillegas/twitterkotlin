package com.twitterkata.domain

import com.twitterkata.domain.users.RegisterUserData

interface JsonUtility {
    fun jsonToRegisterData(bodyAsString: String): RegisterUserData
    fun encode(objectToEncode: Any): String
    fun jsonToUpdateUserData(bodyAsString: String?): UpdateUserData
}
