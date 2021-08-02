package com.twitterkata.domain.users.actions

import com.twitterkata.domain.users.InvalidNicknameException
import com.twitterkata.domain.users.NicknameAlreadyUsedException
import com.twitterkata.domain.users.RegisterUserData
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.IDGenerator

class RegisterUser (private val userRepository: UserRepository,
                    private  val idGenerator: IDGenerator) {

    operator fun invoke(registerUserData: RegisterUserData)  {
        validateNickname(registerUserData.nickname)
        userRepository.save(registerDataToUser(registerUserData))
    }

    private fun registerDataToUser(registerUserData: RegisterUserData): User {
        return User(registerUserData.firstname, registerUserData.surname, registerUserData.nickname, idGenerator.generateId())
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