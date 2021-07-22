package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.Dto
import com.twitterkata.model.User
import com.twitterkata.actions.user_account.exceptions.InexistentUser

class UserRepository : Repository {
    val registeredUsers  = mutableMapOf<String, User>()

    override fun save(dto: Dto) {
        var user: User = dto as User
        registeredUsers.put(user.nickname, user)
    }

    override fun get(nickname: String): User? = registeredUsers.get(nickname)

    override fun update(id: String, data: Dto) {
        var userToUpdate: User = get(id) ?: throw InexistentUser()
        var userData: User = data as User
        userToUpdate.firstName = userData.firstName
        userToUpdate.surname = userData.surname
        registeredUsers.put(id, userToUpdate)
    }
}