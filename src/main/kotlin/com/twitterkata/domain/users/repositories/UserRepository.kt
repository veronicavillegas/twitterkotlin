package com.twitterkata.domain.users.repositories

import com.twitterkata.domain.users.User

interface UserRepository {
    fun save(user: User)
    fun get(nickname: String): User?
    fun update(userData: User)
}