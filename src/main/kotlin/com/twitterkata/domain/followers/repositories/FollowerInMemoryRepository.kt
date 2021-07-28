package com.twitterkata.domain.followers.repositories

import com.twitterkata.domain.users.User

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

    override fun getFollowersOfUser(user: User): List<User> {
        return followersOfUser[user.nickname] ?: listOf()
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