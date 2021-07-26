package com.twitterkata.infraestructure.repositories

import com.twitterkata.infraestructure.MySqlConnection
import com.twitterkata.model.User
import java.sql.*

class UserMySqlRepository: UserRepository {
    private val mySqlConnection = MySqlConnection()

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
            user.firstName = userData.firstName
            user.surname = userData.surname
            updateUser(user)
        }
    }

    private fun updateUser(user: User) {
        val firstname = "firstname = '${user.firstName}'"
        val surname = "surname = '${user.surname}'"
        val query = "UPDATE users SET $firstname, $surname WHERE users.id = ${user.getUserId()}"
        mySqlConnection.executeMySQLQuery(query)
    }

    override fun addFollower(actualUser: User, userToFollow: User) {
        TODO("Not yet implemented")
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

        var user = User(firstname, surname, nickname)
        user.setUserId(userId)

        return user
    }
}