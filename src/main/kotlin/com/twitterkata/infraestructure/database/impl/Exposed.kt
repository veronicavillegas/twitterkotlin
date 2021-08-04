package com.twitterkata.infraestructure.database.impl

import com.twitterkata.domain.users.User
import com.twitterkata.infraestructure.database.TwitterDataBase
import com.twitterkata.infraestructure.database.tables.UsersTable
import com.twitterkata.infraestructure.mappers.UserMapper
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class Exposed() : TwitterDataBase {
    override fun save(user: User) {
        transaction {
            UsersTable.insert {
                it[nickname] = user.nickname
                it[firstname] = user.firstName
                it[surname] = user.surname
            }
        }
    }

    override fun getUserByNickname(nickname: String, userMapper: UserMapper): User? {
        val users = transaction {
            UsersTable.select { UsersTable.nickname eq nickname }.map {
                userMapper.toUser(it)
            }
        }
        if(users.isEmpty()) return null
        return users[0]
    }


    override fun updateUser(user: User) {
        transaction {
            UsersTable.update({ UsersTable.nickname eq user.nickname }) {
                it[firstname] = user.firstName
                it[surname] = user.surname
            }
        }
    }

}