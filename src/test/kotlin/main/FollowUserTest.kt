package main

import com.twitterkata.actions.follow.FollowUser
import com.twitterkata.infraestructure.repositories.UserRepository
import com.twitterkata.model.User
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FollowUserTest {
    private lateinit var followUser: FollowUser

    @Test
    fun userFollowNobody_thenListOfFollowedIsZero() {
        val userRepository = UserRepository()
        userRepository.save(User("Veronica", "Villegas", "@vero"))
        followUser = FollowUser(userRepository)

        assertEquals(0, followUser.getFollowers("@vero").size)
    }

    @Test
    fun userFollowSomebody_thenListOfFollowedIsOne() {
        val user = "@vero"
        val userToFollow = "@maria"
        makeUsersToFollow(user, userToFollow)
        assertEquals(1, followUser.getFollowers(userToFollow).size)
    }

    @Test
    fun whoIsFollowingTo_thenListOfFollowersIsGiven() {
        val user = "@vero"
        val userToFollow = "@maria"
        makeUsersToFollow(user, userToFollow)
        assertEquals(user, followUser.getFollowers(userToFollow).get(0))
    }

    private fun makeUsersToFollow(user: String, userToFollow: String) {
        val userRepository = UserRepository()
        userRepository.save(User("Veronica", "Villegas", user))
        userRepository.save(User("Maria", "Perez", userToFollow))
        followUser = FollowUser(userRepository)
        followUser.followUser(user, userToFollow)
    }

}