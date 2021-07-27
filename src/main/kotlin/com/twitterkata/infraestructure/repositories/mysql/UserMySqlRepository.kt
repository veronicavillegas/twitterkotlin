package com.twitterkata.infraestructure.repositories.mysql

import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User
import java.sql.*

class UserMySqlRepository(connection: MySqlConnection): UserRepository {
    private val mySqlConnection = connection

    override fun save(user: User) {
        val query = getInsertQuery(user)
        mySqlConnection.executeMySQLQuery(query)
    }

    override fun get(nickname: String): User? {
        val query = "SELECT * FROM users WHERE nickname ='$nickname'"
        val resultSet = mySqlConnection.executeMySQLQuery(query)
        if(resultSet?.next() == true) {
            return resultSet?.let { mapUser(it) }
        }
        return null
    }

    override fun update(userData: User) {
        var user = get(userData.nickname)
        if(user != null) {
            updateUser(user.copy(userData.firstName, userData.surname))
        }
    }

    private fun updateUser(user: User) {
        val firstname = "firstname = '${user.firstName}'"
        val surname = "surname = '${user.surname}'"
        val query = "UPDATE users SET $firstname, $surname WHERE users.id = ${user.id}"
        mySqlConnection.executeMySQLQuery(query)
    }

    private fun getInsertQuery(user: User): String {
        val values = "('${user.nickname}', '${user.firstName}', '${user.surname}')"
        return "INSERT INTO users (nickname, firstname, surname) VALUES $values"
    }

    private fun mapUser(resultSet: ResultSet): User? {
        if(resultSet.getString("firstname") == null) {
            return null
        }
        val firstname = resultSet.getString("firstname")
        val surname = resultSet.getString("surname")
        val nickname = resultSet.getString("nickname")
        val userId = resultSet.getInt("id")

        return User(firstname, surname, nickname, userId)
    }
}