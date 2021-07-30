package com.twitterkata.domain.followers.actions

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.followers.repositories.FollowerRepository
import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.model.ResponseResult
import com.twitterkata.domain.users.User

class FollowUser (private val userRepository: UserRepository, private val followerRepository: FollowerRepository) {

    operator fun invoke(nickname: String, nicknameToFollow: String) {
        val followerUser = userRepository.get(nickname)
        val userToFollow = userRepository.get(nicknameToFollow)

        if(followerUser == null || userToFollow == null) {
            throw InexistentUserException()
        }
        followerRepository.addFollower(userToFollow, followerUser)
    }
}