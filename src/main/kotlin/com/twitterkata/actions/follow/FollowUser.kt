package com.twitterkata.actions.follow

import com.twitterkata.infraestructure.repositories.FollowerRepository
import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User

class FollowUser (userRepo: UserRepository, followerRepo: FollowerRepository) {
    private val userRepository: UserRepository = userRepo
    private val followerRepository: FollowerRepository = followerRepo

    fun getFollowers(nickname: String): List<User> {
        val user = userRepository.get(nickname) ?: return listOf()
        return followerRepository.getFollowersOfUser(user.nickname)
    }

    fun followUser(nickname: String, nicknameToFollow: String) {
        val followerUser = userRepository.get(nickname)
        val userToFollow = userRepository.get(nicknameToFollow)
        if(followerUser != null && userToFollow != null) {
            followerRepository.addFollower(userToFollow, followerUser)
        }
    }
}