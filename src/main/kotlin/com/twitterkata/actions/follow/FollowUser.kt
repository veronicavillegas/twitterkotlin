package com.twitterkata.actions.follow

import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User

class FollowUser (userRepo: UserRepository) {
    private val userRepository: UserRepository = userRepo

    fun getFollowers(nickname: String): List<String> {
        val user: User = userRepository.get(nickname) as User
        return user.followers
    }

    fun followUser(user: String, userToFollow: String) {
        val actualUser = userRepository.get(user) as User
        val userToFollow = userRepository.get(userToFollow) as User

        userToFollow.followers.add(actualUser.nickname)
        userRepository.save(userToFollow)
    }
}