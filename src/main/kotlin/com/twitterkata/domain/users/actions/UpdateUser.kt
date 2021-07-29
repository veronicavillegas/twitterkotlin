package com.twitterkata.domain.users.actions

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.model.ResponseResult

class UpdateUser (private val userRepository: UserRepository){
    operator fun invoke(user: User) {
        if(userRepository.get(user.nickname) == null) {
            throw InexistentUserException()
        }
        userRepository.update(user)
    }
}