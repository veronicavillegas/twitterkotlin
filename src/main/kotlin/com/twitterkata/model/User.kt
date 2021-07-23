package com.twitterkata.model

class User(var firstName: String, var surname: String, val nickname: String) {
    var followers = mutableListOf<String>()

    fun getFollowersOfUser() : List<String> {
        var followersToReturn = mutableListOf<String>()
        val iterator = followers.iterator()
        while (iterator.hasNext()) {
            followersToReturn.add(iterator.next())
        }
        return followersToReturn
    }
}