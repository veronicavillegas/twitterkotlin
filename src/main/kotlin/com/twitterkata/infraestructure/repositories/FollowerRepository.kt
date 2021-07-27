package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.User

interface FollowerRepository {
    fun addFollower(userToFollow: User, followerUser: User)
    fun getFollowersOfUser(nickname: String): List<User>
}