package com.twitterkata.domain.users.actions

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.model.ResponseResult

class RegisterUser (private val userRepository: UserRepository){
    operator fun invoke(user: User) : ResponseResult {
        val validationResult = validateNickname(user.nickname)
        if(validationResult.status == Status.FAIL) {
            return validationResult
        }
        userRepository.save(user)
        return ResponseResult(Status.OK, Messages.OK)
    }

    private fun validateNickname(nickname: String) : ResponseResult {
        if(isNotValidNickname(nickname)) {
            return ResponseResult(Status.FAIL, Messages.INVALID_NICKNAME)
        }
        if(isNicknameAlreadyUsed(nickname)) {
            return ResponseResult(Status.FAIL, Messages.NICKNAME_ALREADY_EXISTENT)
        }
        return ResponseResult(Status.OK, Messages.OK)
    }

    private fun isNotValidNickname(nickname: String) = nickname.isNullOrEmpty() || nickname.isBlank()

    private fun isNicknameAlreadyUsed(nickname: String) = userRepository.get(nickname) != null
}