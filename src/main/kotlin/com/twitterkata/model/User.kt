package com.twitterkata.model

data class User(val firstName: String, val surname: String, val nickname: String, val id: Int=0) {

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