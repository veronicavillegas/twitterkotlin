package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.User

class UserInMemoryRepository: UserRepository {
    private val registeredUsers  = mutableMapOf<String, User>()

    override fun save(user: User) {
        registeredUsers.put(user.nickname, user)
    }

    override fun get(nickname: String): User? = registeredUsers.get(nickname)

    override fun update(userData: User) {
        var userToUpdate: User = get(userData.nickname) ?: return
        userToUpdate.firstName = userData.firstName
        userToUpdate.surname = userData.surname
        userToUpdate.followers = userData.followers
        registeredUsers.put(userData.nickname, userToUpdate)
    }

    override fun addFollower(actualUser: User, userToFollow: User) {
        actualUser.followers.add(userToFollow)
        registeredUsers.put(actualUser.nickname, actualUser)
    }
}