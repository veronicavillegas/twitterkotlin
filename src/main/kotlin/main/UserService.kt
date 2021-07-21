package main

import main.dto.Dto
import main.dto.User
import main.exception.NicknameAlreadyUsed
import main.persistence.Repository
import main.persistence.impl.UserRepository

class UserService {
    private var userRepository: Repository = UserRepository()
    private var validator: Validator = Validator()

    fun registerUser(user: User) {
        validator.validateNickname(user.nickname)
        checkNicknameAlreadyUsed(user.nickname)
        userRepository.save(user)
    }

    fun getUser(nickname: String) : Dto? = userRepository.get(nickname)

    fun updateUser(user: User) {
        userRepository.update(user.nickname, user)
    }

    private fun checkNicknameAlreadyUsed(nickname: String) {
        if (userRepository.get(nickname) != null) {
            throw NicknameAlreadyUsed()
        }
    }

    fun getFollowedUsers(nickname: String): MutableList<String> {
        var user: User = userRepository.get(nickname) as User
        return user.followedUsers
    }

    fun followUser(user: String, userToFollow: String) {
        var user: User = userRepository.get(user) as User
        user.followedUsers.add(userToFollow)
        userRepository.save(user)
    }
}