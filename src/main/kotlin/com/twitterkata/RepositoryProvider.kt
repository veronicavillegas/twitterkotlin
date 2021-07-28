package com.twitterkata

import com.twitterkata.infraestructure.MySqlConnection
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.domain.users.repositories.UserMySqlRepository

object RepositoryProvider{
    private val mySqlConnection = MySqlConnection().apply { initConnection() }

    fun provideSQLRepository(): UserRepository = UserMySqlRepository(mySqlConnection)
}
