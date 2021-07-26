package com.twitterkata.actions.follow

import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User

class FollowUser (userRepo: UserRepository) {
    private val userRepository: UserRepository = userRepo

    fun getFollowers(nickname: String): List<User> {
        val user = userRepository.get(nickname)
        return user?.getFollowersOfUser() ?: listOf(User("", "", ""))
    }

    fun followUser(nickname: String, nicknameToFollow: String) {
        val actualUser = userRepository.get(nickname)
        val userToFollow = userRepository.get(nicknameToFollow)
        if(actualUser != null && userToFollow != null) {
            userRepository.addFollower(userToFollow, actualUser)
        }
    }
}