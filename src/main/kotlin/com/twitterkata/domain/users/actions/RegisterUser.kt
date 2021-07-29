package com.twitterkata.domain.users.actions

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.InvalidNicknameException
import com.twitterkata.domain.users.NicknameAlreadyUsedException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.model.ResponseResult

class RegisterUser (private val userRepository: UserRepository){
    operator fun invoke(user: User)  {
        validateNickname(user.nickname)
        userRepository.save(user)
    }

    private fun validateNickname(nickname: String) {
        if(isNotValidNickname(nickname)) {
            throw InvalidNicknameException()
        }
        if(isNicknameAlreadyUsed(nickname)) {
            throw NicknameAlreadyUsedException()
        }
    }

    private fun isNotValidNickname(nickname: String) = nickname.isNullOrEmpty() || nickname.isBlank()

    private fun isNicknameAlreadyUsed(nickname: String) = userRepository.get(nickname) != null
}