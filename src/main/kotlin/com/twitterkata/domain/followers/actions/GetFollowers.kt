package com.twitterkata.domain.followers.actions

import com.twitterkata.domain.followers.repositories.FollowerRepository
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository

class GetFollowers(private val userRepository: UserRepository, private val followerRepository: FollowerRepository) {

    operator fun invoke(nickname: String): List<User> {
        val user = userRepository.get(nickname) ?: return listOf()
        return followerRepository.getFollowersOfUser(user)
    }
}