package com.twitterkata.actions.user_account

import com.twitterkata.model.Dto
import com.twitterkata.model.User
import com.twitterkata.infraestructure.repositories.Repository
import com.twitterkata.infraestructure.repositories.UserRepository

class UpdateUser {
    private var userRepository: Repository = UserRepository()
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

    fun getFollowers(nickname: String): MutableList<String> {
        var user: User = userRepository.get(nickname) as User
        return user.followers
    }

    fun followUser(user: String, userToFollow: String) {
        var actualUser = userRepository.get(user) as User
        var userToFollow = userRepository.get(userToFollow) as User

        userToFollow.followers.add(actualUser.nickname)
        userRepository.save(userToFollow)
    }
}