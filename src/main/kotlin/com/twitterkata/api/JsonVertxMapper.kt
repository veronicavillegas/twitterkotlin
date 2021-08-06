package com.twitterkata.api

import com.twitterkata.domain.users.requestData.UpdateUserData
import com.twitterkata.domain.users.requestData.RegisterUserData
import io.vertx.core.json.Json

class JsonVertxMapper: JsonMapper {
    override fun jsonToRegisterData(bodyAsString: String) =
        Json.decodeValue(bodyAsString, RegisterUserData::class.java)

    override fun encode(objectToEncode: Any) =
        Json.encodePrettily(objectToEncode)

    override fun jsonToUpdateUserData(bodyAsString: String?) =
        Json.decodeValue(bodyAsString, UpdateUserData::class.java)
}