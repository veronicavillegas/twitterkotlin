package com.twitterkata.actions.followers

import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User

class FollowUser (userRepo: UserRepository) {
    private var userRepository: UserRepository = userRepo

    fun getFollowers(nickname: String): MutableList<String> {
        var user: User = userRepository.get(nickname) as User
        return user.followers
    }

    fun followUser(user: String, userToFollow: String) {
        var actualUser = userRepository.get(user) as User
        var userToFollow = userRepository.get(userToFollow) as User

        userToFollow.followers.add(actualUser.nickname)
        userRepository.save(userToFollow)
    }
}