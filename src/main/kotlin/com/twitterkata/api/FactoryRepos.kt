package com.twitterkata.api

import com.twitterkata.domain.followers.repositories.FollowerMySqlRepository
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.database.impl.ExposedDataBase
import com.twitterkata.infraestructure.mappers.impl.UserMapperExposedImpl
import com.twitterkata.infraestructure.repositories.user.UserInMemoryRepository
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository

object FactoryRepos {
    private val databaseType = "MY_SQL"

    fun getUserRepository(): UserRepository {
        if (databaseType == "MY_SQL")
            return UserMySqlRepository(UserMapperExposedImpl(), ExposedDataBase())
        else return UserInMemoryRepository()
    }

    fun getFollowerRepository() = FollowerMySqlRepository()
}