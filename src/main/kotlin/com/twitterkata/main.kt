package com.twitterkata

import com.twitterkata.api.VertxServer
import com.twitterkata.api.Factory
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.api.JsonVertxMapper
import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.followers.repositories.FollowerMySqlRepository
import com.twitterkata.domain.followers.repositories.FollowerRepository
import com.twitterkata.domain.users.actions.GetUser
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.database.MySqlConnection
import com.twitterkata.infraestructure.database.impl.Exposed
import com.twitterkata.infraestructure.mappers.impl.UserMapperExposedImpl
import com.twitterkata.infraestructure.repositories.user.UserInMemoryRepository
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository

fun main() {
    VertxServer(FactoryImpl()).start()
}

class FactoryImpl: Factory {
    private val databaseType = "MY_SQL"

    override fun getJsonMapper() = JsonVertxMapper()

    override fun getRegisterUserAction() = RegisterUser(getUserRepository())

    override fun getUpdateUserAction() = UpdateUser(getUserRepository())

    override fun initDatabaseConnection() = MySqlConnection().initConnection()

    override fun getUserAction() = GetUser(getUserRepository())

    override fun getFollowUserAction() = FollowUser(getUserRepository(), getFollowerRepository())

    private fun getFollowerRepository() = FollowerMySqlRepository()

    private fun getUserRepository(): UserRepository {
        if (databaseType == "MY_SQL")
            return UserMySqlRepository(UserMapperExposedImpl(), Exposed())
        else return UserInMemoryRepository()
    }
}