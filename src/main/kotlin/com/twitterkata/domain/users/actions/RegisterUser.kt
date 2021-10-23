package com.twitterkata.domain.users.actions

import com.twitterkata.domain.users.exceptions.InvalidNicknameException
import com.twitterkata.domain.users.exceptions.NicknameAlreadyUsedException
import com.twitterkata.domain.users.requestData.RegisterUserData
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository

class RegisterUser (private val userRepository: UserRepository) {

    operator fun invoke(registerUserData: RegisterUserData)  {
        validateNickname(registerUserData.nickname)
        userRepository.save(registerDataToUser(registerUserData))
    }

    private fun registerDataToUser(registerUserData: RegisterUserData): User {
        return User(registerUserData.firstname, registerUserData.surname, registerUserData.nickname)
    }


    private fun validateNickname(nickname: String) {
        when{
            isNotValidNickname(nickname) -> throw InvalidNicknameException()
            isNicknameAlreadyUsed(nickname) -> throw NicknameAlreadyUsedException()
        }
    }

    private fun isNotValidNickname(nickname: String) = nickname.isNullOrEmpty() || nickname.isBlank()

    private fun isNicknameAlreadyUsed(nickname: String) = userRepository.get(nickname) != null
}