package com.twitterkata.actions.user_account

import com.twitterkata.model.User
import com.twitterkata.infraestructure.repositories.UserRepository

class UpdateUser (userRepo: UserRepository){
    private val userRepository: UserRepository = userRepo

    fun registerUser(user: User) : ResultMessage {
        val validationResult = validateNickname(user.nickname)
        if(validationResult.status == Status.FAIL) {
            return validationResult
        }
        userRepository.save(user)
        return ResultMessage(Status.OK, Messages.OK)
    }

    fun getUser(nickname: String) : User? = userRepository.get(nickname)

    fun updateUser(user: User) : ResultMessage {
        if(userRepository.get(user.nickname) == null) {
            return ResultMessage(Status.FAIL, Messages.INEXISTENT_USER)
        }
        userRepository.update(user.nickname, user)
        return ResultMessage(Status.OK, Messages.OK)
    }

    private fun validateNickname(nickname: String) : ResultMessage {
        if(isNotValidNickname(nickname)) {
            return ResultMessage(Status.FAIL, Messages.INVALID_NICKNAME)
        }
        if(isNicknameAlreadyUsed(nickname)) {
            return ResultMessage(Status.FAIL, Messages.NICKNAME_ALREADY_EXISTENT)
        }
        return ResultMessage(Status.OK, Messages.OK)
    }

    private fun isNotValidNickname(nickname: String) = nickname.isNullOrEmpty() || nickname.isBlank()

    private fun isNicknameAlreadyUsed(nickname: String) = userRepository.get(nickname) != null
}