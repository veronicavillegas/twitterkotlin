package com.twitterkata.domain

import com.twitterkata.Factory
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.infraestructure.JsonVertxUtility
import com.twitterkata.infraestructure.database.MySqlConnection
import com.twitterkata.infraestructure.database.impl.Exposed
import com.twitterkata.infraestructure.mappers.impl.UserMapperExposedImpl
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository

class FactoryImpl: Factory {
    override fun getJsonUtility() = JsonVertxUtility()

    override fun getRegisterUserAction() = RegisterUser(getUserRepository())

    override fun getUpdateUserAction() = UpdateUser(getUserRepository())

    override fun initDatabaseConnection() = MySqlConnection().initConnection()

    private fun getUserRepository() = UserMySqlRepository(UserMapperExposedImpl(), Exposed())
}