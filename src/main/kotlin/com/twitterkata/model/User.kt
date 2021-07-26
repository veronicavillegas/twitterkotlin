package com.twitterkata.model

class User(var firstName: String, var surname: String, val nickname: String) {
    private var id: Int = 0

    var followers = mutableListOf<User>()

    fun getFollowersOfUser() : List<User> {
        var followersToReturn = mutableListOf<User>()
        val iterator = followers.iterator()
        while (iterator.hasNext()) {
            followersToReturn.add(iterator.next())
        }
        return followersToReturn
    }

    fun setUserId(id: Int) {
        this.id = id
    }

    fun getUserId() = this.id
}