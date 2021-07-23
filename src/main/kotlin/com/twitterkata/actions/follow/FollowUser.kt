package com.twitterkata.actions.follow

import com.twitterkata.infraestructure.repositories.UserRepository

class FollowUser (userRepo: UserRepository) {
    private val userRepository: UserRepository = userRepo

    fun getFollowers(nickname: String): List<String> {
        val user = userRepository.get(nickname)
        return user?.getFollowersOfUser() ?: listOf("")
    }

    fun followUser(user: String, userToFollow: String) {
        val actualUser = userRepository.get(user)
        val userToFollow = userRepository.get(userToFollow)
        if(actualUser != null && userToFollow != null) {
            userToFollow.followers.add(actualUser.nickname)
            userRepository.save(userToFollow)
        }
    }
}