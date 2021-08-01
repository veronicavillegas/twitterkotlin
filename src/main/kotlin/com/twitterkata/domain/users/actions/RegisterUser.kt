package com.twitterkata.domain.users.actions

import com.twitterkata.domain.users.InvalidNicknameException
import com.twitterkata.domain.users.NicknameAlreadyUsedException
import com.twitterkata.domain.users.RegisterData
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.IDGenerator
import java.util.*

class RegisterUser (private val userRepository: UserRepository, private  val idGenerator: IDGenerator){
    operator fun invoke(registerData: RegisterData)  {
        validateNickname(registerData.nickname)
        userRepository.save(registerDataToUser(registerData))
    }

    private fun registerDataToUser(registerData: RegisterData): User {
        return User(registerData.firstName, registerData.surname, registerData.nickname, idGenerator.generateId())
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