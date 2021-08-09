package com.twitterkata.infraestructure.repositories.user

import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.database.TwitterDataBase
import com.twitterkata.infraestructure.mappers.UserMapper


class UserMySqlRepository(private val userMapper: UserMapper, private val database: TwitterDataBase): UserRepository {

    override fun save(user: User) {
        database.save(user)
    }

    override fun get(nickname: String): User? {
        return database.getUserByNickname(nickname, userMapper)
    }

    override fun update(userData: User) {
        database.updateUser(userData)
    }
}


