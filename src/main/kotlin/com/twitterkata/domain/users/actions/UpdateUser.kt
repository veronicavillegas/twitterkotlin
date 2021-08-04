package com.twitterkata.domain.users.actions

import com.twitterkata.domain.UpdateUserData
import com.twitterkata.domain.users.exceptions.InexistentUserException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository

class UpdateUser (private val userRepository: UserRepository){
    operator fun invoke(nickname: String, updateUserData: UpdateUserData) {
        val userToUpdate = userRepository.get(nickname)
        if(userToUpdate == null) {
            throw InexistentUserException()
        }
        userRepository.update(getUserToUpdate(userToUpdate, updateUserData))
    }

    private fun getUserToUpdate(userToUpdate: User, data: UpdateUserData) =
        userToUpdate.copy(data.firstname, data.surname)
}