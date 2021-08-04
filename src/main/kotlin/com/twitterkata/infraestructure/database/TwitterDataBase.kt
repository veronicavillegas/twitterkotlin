package com.twitterkata.infraestructure.database

import com.twitterkata.domain.users.User
import com.twitterkata.infraestructure.database.tables.UsersTable
import com.twitterkata.infraestructure.mappers.UserMapper

interface TwitterDataBase {
    fun save(user: User)
    fun getUserByNickname(nickname: String, userMapper: UserMapper): User
    fun updateUser(user: User)
}
