package com.twitterkata.infraestructure.repositories.memory

import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User

class UserInMemoryRepository: UserRepository {
    private val registeredUsers  = mutableMapOf<String, User>()

    override fun save(user: User) {
        registeredUsers.put(user.nickname, user)
    }

    override fun get(nickname: String): User? = registeredUsers.get(nickname)

    override fun update(userData: User) {
        val userToUpdate: User = get(userData.nickname) ?: return
        registeredUsers.put(userData.nickname, userToUpdate.copy(userData.firstName, userData.surname, userData.nickname))
    }
}