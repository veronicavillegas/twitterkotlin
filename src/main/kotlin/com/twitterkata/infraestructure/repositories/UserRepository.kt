package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.User

interface UserRepository {
    fun save(user: User)
    fun get(nickname: String): User?
    fun update(userData: User)
}