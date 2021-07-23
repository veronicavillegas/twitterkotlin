package com.twitterkata.model

class User(var firstName: String, var surname: String, val nickname: String) {
    var followers = mutableListOf<String>()

    fun getFollowers() : ArrayList<String> {
        var followersToReturn = arrayListOf<String>()
        val iterator = followers.iterator()
        while (iterator.hasNext()) {
            followersToReturn.add(iterator.next())
        }
        return followersToReturn
    }
}