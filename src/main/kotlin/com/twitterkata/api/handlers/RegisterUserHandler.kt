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
    val connection = MySqlConnection()
    val userRepository = UserMySqlRepository(connection)
    val registerUser = RegisterUser(userRepository = userRepository, idGenerator = UUIDGenerator())

    override fun handle(event: RoutingContext) {
        registerUser.invoke(getRegisterData(event) )
        event.response().end()
    }

    private fun getRegisterData(event: RoutingContext) =
        Gson().fromJson(event.getBodyAsString(""), RegisterData::class.java)

}
