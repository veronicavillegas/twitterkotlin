package com.twitterkata.infraestructure.mappers.impl

import com.twitterkata.domain.users.User
import com.twitterkata.infraestructure.database.tables.UsersTable
import com.twitterkata.infraestructure.mappers.UserMapper
import org.jetbrains.exposed.sql.ResultRow

class UserMapperExposedImpl: UserMapper {
    override fun toUser(it: ResultRow) = User(
        it[UsersTable.firstname],
        it[UsersTable.surname],
        it[UsersTable.nickname],
        it[UsersTable.id].value.toString()
    )
}