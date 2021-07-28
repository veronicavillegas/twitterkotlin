package com.twitterkata.domain.users.actions

import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository

class GetUser(private val userRepository: UserRepository) {
    operator fun invoke (nickname: String) : User? = userRepository.get(nickname)
}