package com.twitterkata.infraestructure.database.tables

import com.twitterkata.domain.users.User
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UsersTable : UUIDTable("users") {
    val nickname = varchar("nickname",100)
    val firstname = varchar("firstname",100)
    val surname = varchar("surname",100)
}