package com.twitterkata.actions.user_account

import com.twitterkata.actions.user_account.enums.Messages
import com.twitterkata.actions.user_account.enums.Status
import com.twitterkata.model.User
import com.twitterkata.infraestructure.repositories.UserRepository

class UpdateUser (userRepo: UserRepository){
    private val userRepository: UserRepository = userRepo

    fun registerUser(user: User) : UpdateUserResult {
        val validationResult = validateNickname(user.nickname)
        if(validationResult.status == Status.FAIL) {
            return validationResult
        }
        userRepository.save(user)
        return UpdateUserResult(Status.OK, Messages.OK)
    }

    fun getUser(nickname: String) : User? = userRepository.get(nickname)

    fun updateUser(user: User) : UpdateUserResult {
        if(userRepository.get(user.nickname) == null) {
            return UpdateUserResult(Status.FAIL, Messages.INEXISTENT_USER)
        }
        userRepository.update(user.nickname, user)
        return UpdateUserResult(Status.OK, Messages.OK)
    }

    private fun validateNickname(nickname: String) : UpdateUserResult {
        if(isNotValidNickname(nickname)) {
            return UpdateUserResult(Status.FAIL, Messages.INVALID_NICKNAME)
        }
        if(isNicknameAlreadyUsed(nickname)) {
            return UpdateUserResult(Status.FAIL, Messages.NICKNAME_ALREADY_EXISTENT)
        }
        return UpdateUserResult(Status.OK, Messages.OK)
    }

    private fun isNotValidNickname(nickname: String) = nickname.isNullOrEmpty() || nickname.isBlank()

    private fun isNicknameAlreadyUsed(nickname: String) = userRepository.get(nickname) != null
}