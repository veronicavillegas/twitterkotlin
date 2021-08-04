package com.twitterkata.infraestructure.mappers

import com.twitterkata.domain.users.User
import org.jetbrains.exposed.sql.ResultRow

interface UserMapper {
    fun toUser(it: ResultRow): User
}
