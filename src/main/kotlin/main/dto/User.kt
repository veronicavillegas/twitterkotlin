package main.dto

class User(var firstName: String, var surname: String, var nickname: String) : Dto() {
    var followedUsers = mutableListOf<String>()
}