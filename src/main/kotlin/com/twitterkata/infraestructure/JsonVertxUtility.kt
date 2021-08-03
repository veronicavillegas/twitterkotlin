package com.twitterkata.infraestructure

import com.twitterkata.domain.JsonUtility
import com.twitterkata.domain.UpdateUserData
import com.twitterkata.domain.users.RegisterUserData
import io.vertx.core.json.Json

class JsonVertxUtility: JsonUtility {
    override fun jsonToRegisterData(bodyAsString: String) =
        Json.decodeValue(bodyAsString, RegisterUserData::class.java)

    override fun encode(objectToEncode: Any) =
        Json.encodePrettily(objectToEncode)

    override fun jsonToUpdateUserData(bodyAsString: String?) =
        Json.decodeValue(bodyAsString, UpdateUserData::class.java)
}