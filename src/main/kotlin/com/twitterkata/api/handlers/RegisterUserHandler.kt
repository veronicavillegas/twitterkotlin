package com.twitterkata.api.handlers

import com.google.gson.Gson
import com.twitterkata.domain.users.RegisterData
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.repositories.UserMySqlRepository
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.MySqlConnection
import com.twitterkata.infraestructure.UUIDGenerator
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class RegisterUserHandler : Handler<RoutingContext> {
    // TODO: Reemplazar por factory
    val registerUser = RegisterUser(userRepository = UserMySqlRepository(MySqlConnection()), idGenerator = UUIDGenerator())
    override fun handle(event: RoutingContext) {
        val registerData = Gson().fromJson(event.bodyAsString, RegisterData::class.java)
         registerUser.invoke(registerData)
        event.response().end()
    }

}
