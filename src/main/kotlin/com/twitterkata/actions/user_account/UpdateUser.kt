package com.twitterkata.actions.user_account

import com.twitterkata.model.Dto
import com.twitterkata.model.User
import com.twitterkata.infraestructure.repositories.Repository
import com.twitterkata.infraestructure.repositories.UserRepository

class UpdateUser (userRepo: UserRepository){
    private var userRepository: Repository = userRepo
    private var validator: NicknameValidator = NicknameValidator()

    fun registerUser(user: User) {
        var otherUserSameNickname = userRepository.get(user.nickname)
        validator.validateNickname(user.nickname, otherUserSameNickname as User?)
        userRepository.save(user)
    }

    fun getUser(nickname: String) : Dto? = userRepository.get(nickname)

    fun updateUser(user: User) {
        userRepository.update(user.nickname, user)
    }
}