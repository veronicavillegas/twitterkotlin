package com.twitterkata.domain.followers.repositories

import com.twitterkata.domain.users.User

interface FollowerRepository {
    fun addFollower(userToFollow: User, followerUser: User)
    fun getFollowersOfUser(user: User): List<User>
}