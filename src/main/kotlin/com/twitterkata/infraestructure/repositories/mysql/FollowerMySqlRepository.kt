package com.twitterkata.infraestructure.repositories.mysql

import com.twitterkata.infraestructure.repositories.FollowerRepository
import com.twitterkata.model.User

class FollowerMySqlRepository: FollowerRepository {
    override fun addFollower(actualUser: User, userToFollow: User) {
        TODO("Not yet implemented")
    }

    override fun getFollowersOfUser(nickname: String): List<User> {
        TODO("Not yet implemented")
    }
}