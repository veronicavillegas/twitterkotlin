package com.twitterkata.domain.users.repositories

import com.twitterkata.domain.users.User

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