package com.twitterkata.actions.user_account

import com.twitterkata.actions.user_account.exceptions.InvalidNickname
import com.twitterkata.actions.user_account.exceptions.NicknameAlreadyUsed
import com.twitterkata.model.User
import com.twitterkata.infraestructure.repositories.UserRepository

class UpdateUser (userRepo: UserRepository){
    private val userRepository: UserRepository = userRepo

    fun registerUser(user: User) {
        val otherUserSameNickname = userRepository.get(user.nickname)
        validateNickname(user.nickname, otherUserSameNickname as User?)
        userRepository.save(user)
    }

    fun getUser(nickname: String) : User? = userRepository.get(nickname)

    fun updateUser(user: User) {
        userRepository.update(user.nickname, user)
    }

    fun validateNickname(nickname: String, otherUserSameNickname: User?) {
        checkNickname(nickname)
        checkNicknameAlreadyUsed(otherUserSameNickname)
    }

    private fun checkNickname(nickname: String) {
        if (nickname.isNullOrEmpty() || nickname.isBlank()) {
            throw InvalidNickname()
        }
    }

    private fun checkNicknameAlreadyUsed(otherUserSameNickname: User?) {
        if (otherUserSameNickname != null) {
            throw NicknameAlreadyUsed()
        }
    }
}