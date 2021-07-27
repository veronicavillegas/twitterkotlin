package com.twitterkata.infraestructure.repositories.memory

import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User

class UserInMemoryRepository: UserRepository {
    private val registeredUsers  = mutableMapOf<String, User>()

    override fun save(user: User) {
        registeredUsers[user.nickname] = user
    }

    override fun get(nickname: String): User? = registeredUsers[nickname]

    override fun update(userData: User) {
        val userToUpdate: User = get(userData.nickname) ?: return
        registeredUsers[userData.nickname] = userToUpdate.copy(
            firstName = userData.firstName,
            surname = userData.surname,
            nickname = userData.nickname
        )
    }
}