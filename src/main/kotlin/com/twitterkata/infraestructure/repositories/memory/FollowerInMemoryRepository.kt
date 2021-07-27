package com.twitterkata.infraestructure.repositories.memory

import com.twitterkata.infraestructure.repositories.FollowerRepository
import com.twitterkata.model.User

class FollowerInMemoryRepository: FollowerRepository {
    private val followersOfUser  = mutableMapOf<String, List<User>>()

    override fun addFollower(userToFollow: User, followerUser: User) {
        val followers = followersOfUser[userToFollow.nickname]
        if (followers != null) {
            addFollower(followers, followerUser, userToFollow)
        } else {
            initFollowerListWithFollower(userToFollow, followerUser)
        }
    }

    override fun getFollowersOfUser(nickname: String): List<User> {
        return followersOfUser[nickname] ?: listOf()
    }

    private fun initFollowerListWithFollower(userToFollow: User, followerUser: User) {
        followersOfUser[userToFollow.nickname] = listOf(followerUser)
    }

    private fun addFollower(
        followers: List<User>,
        followerUser: User,
        userToFollow: User
    ) {
        var mutableListOfFollowers = followers.toMutableList()
        mutableListOfFollowers.add(followerUser)
        followersOfUser[userToFollow.nickname] = mutableListOfFollowers.toList()
    }
}