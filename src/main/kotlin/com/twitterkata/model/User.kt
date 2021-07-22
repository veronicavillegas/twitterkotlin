package com.twitterkata.model

class User(var firstName: String, var surname: String, var nickname: String) : Dto {
    var followedUsers = mutableListOf<String>()
}