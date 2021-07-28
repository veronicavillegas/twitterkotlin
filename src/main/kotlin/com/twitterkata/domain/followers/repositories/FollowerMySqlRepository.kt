package com.twitterkata.domain.followers.repositories

import com.twitterkata.infraestructure.DataBaseConnection
import com.twitterkata.domain.users.User

class FollowerMySqlRepository(connection: DataBaseConnection): FollowerRepository {
    private val mySqlConnection = connection

    override fun addFollower(userToFollow: User, followerUser: User) {
        val values = "${userToFollow.id}, ${followerUser.id}"
        mySqlConnection.executeMySQLQuery(getInsertQuery(values))
    }

    override fun getFollowersOfUser(user: User): List<User> {
        mySqlConnection.executeMySQLQuery(getFollowersOfUserQuery(user.id))
        return listOf()
    }

    private fun getFollowersOfUserQuery(userId: Int) = "SELECT id_follower FROM followers WHERE id_user = $userId"

    private fun getInsertQuery(values: String): String {
        return "INSERT INTO followers (id_user, id_follower) VALUES ($values)"
    }
}