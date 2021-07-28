package com.twitterkata

import com.twitterkata.infraestructure.MySqlConnection
import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository

object RepositoryProvider{
    private val mySqlConnection = MySqlConnection().apply { initConnection() }

    fun provideSQLRepository(): UserRepository = UserMySqlRepository(mySqlConnection)
}
