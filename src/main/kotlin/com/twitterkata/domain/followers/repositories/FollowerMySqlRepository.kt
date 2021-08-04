package com.twitterkata.domain.followers.repositories

import com.twitterkata.domain.DataBaseConnection
import com.twitterkata.domain.users.User

class FollowerMySqlRepository(connection: DataBaseConnection): FollowerRepository {
    private val mySqlConnection = connection

    override fun addFollower(userToFollow: User, followerUser: User) {
        TODO("NOT IMPLEMENTED")
    }

    override fun getFollowersOfUser(user: User): List<User> {
        TODO("NOT IMPLEMENTED")
    }

}