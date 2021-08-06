package com.twitterkata

import com.twitterkata.api.VertxServer
import com.twitterkata.api.Factory
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.api.JsonVertxMapper
import com.twitterkata.infraestructure.database.MySqlConnection
import com.twitterkata.infraestructure.database.impl.Exposed
import com.twitterkata.infraestructure.mappers.impl.UserMapperExposedImpl
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository

fun main() {
    VertxServer(FactoryImpl()).start()
}

class FactoryImpl: Factory {
    override fun getJsonUtility() = JsonVertxMapper()

    override fun getRegisterUserAction() = RegisterUser(getUserRepository())

    override fun getUpdateUserAction() = UpdateUser(getUserRepository())

    override fun initDatabaseConnection() = MySqlConnection().initConnection()

    private fun getUserRepository() = UserMySqlRepository(UserMapperExposedImpl(), Exposed())
}