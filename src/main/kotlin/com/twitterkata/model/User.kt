package com.twitterkata.model

class User(var firstName: String, var surname: String, val nickname: String) {
    private val id: Int
        get() {
            return id
        }

    var followers = mutableListOf<User>()

    fun getFollowersOfUser() : List<User> {
        var followersToReturn = mutableListOf<User>()
        val iterator = followers.iterator()
        while (iterator.hasNext()) {
            followersToReturn.add(iterator.next())
        }
        return followersToReturn
    }
}