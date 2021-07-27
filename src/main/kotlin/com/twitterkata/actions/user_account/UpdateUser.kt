package com.twitterkata.actions.user_account

import com.twitterkata.actions.user_account.enums.Messages
import com.twitterkata.actions.user_account.enums.Status
import com.twitterkata.model.User
import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.ResponseResult

class UpdateUser (userRepo: UserRepository){
    private val userRepository = userRepo

    fun registerUser(user: User) : ResponseResult {
        val validationResult = validateNickname(user.nickname)
        if(validationResult.status == Status.FAIL) {
            return validationResult
        }
        userRepository.save(user)
        return ResponseResult(Status.OK, Messages.OK)
    }

    fun getUser(nickname: String) : User? = userRepository.get(nickname)

    fun updateUser(user: User) : ResponseResult {
        if(userRepository.get(user.nickname) == null) {
            return ResponseResult(Status.FAIL, Messages.INEXISTENT_USER)
        }
        userRepository.update(user)
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