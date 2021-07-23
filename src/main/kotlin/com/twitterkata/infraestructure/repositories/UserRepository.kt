package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.User
import com.twitterkata.actions.user_account.exceptions.InexistentUser

class UserRepository {
    val registeredUsers  = mutableMapOf<String, User>()

    fun save(user: User) {
        registeredUsers.put(user.nickname, user)
    }

    fun get(nickname: String): User? = registeredUsers.get(nickname)

    fun update(id: String, userData: User) {
        var userToUpdate: User = get(id) ?: return
        userToUpdate.firstName = userData.firstName
        userToUpdate.surname = userData.surname
        registeredUsers.put(id, userToUpdate)
    }
}