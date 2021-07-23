package com.twitterkata.model

class User(var firstName: String, var surname: String, val nickname: String) {
    val followers = mutableListOf<String>()
}