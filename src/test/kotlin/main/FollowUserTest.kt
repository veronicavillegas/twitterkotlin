package main

import com.twitterkata.actions.follow.FollowUser
import com.twitterkata.infraestructure.repositories.UserInMemoryRepository
import com.twitterkata.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FollowUserTest {
    private val userRepository = UserInMemoryRepository()
    private val followUser = FollowUser(userRepository)
    private val user = "@vero"
    private val userToFollow = "@maria"
    private val otherFollower = "@pedro"

    @BeforeEach
    fun setUp() {
        userRepository.save(User("Veronica", "Villegas", "@vero"))
        userRepository.save(User("Maria", "Perez", "@maria"))
        userRepository.save(User("Pedro", "Rodriguez", "@pedro"))
    }

    @Test
    fun whenFollowerNotExists_thenFollowerNotExists() {
        followUser.followUser("@noexiste", "@maria")
    }
    @Test
    fun userFollowNobody_thenListOfFollowedIsZero() {
        assertEquals(0, followUser.getFollowers(user).size)
    }

    @Test
    fun userFollowSomebody_thenListOfFollowedIsOne() {
        makeUsersToFollow(user, userToFollow, userRepository)
        assertEquals(1, followUser.getFollowers(userToFollow).size)
    }

    @Test
    fun whenIHaveTwoFollowers_thenSizeOfFollowersListIsTwo() {
        makeUsersToFollow(user, userToFollow, userRepository)
        makeUsersToFollow(otherFollower, userToFollow, userRepository)
        assertEquals(2, followUser.getFollowers(userToFollow).size)
    }

    @Test
    fun whenIWantToKnowWhoIsFollowingTo_thenListOfFollowersIsGiven() {
        makeUsersToFollow(user, userToFollow, userRepository)
        assertEquals(user, followUser.getFollowers(userToFollow).get(0).nickname)
    }

    private fun makeUsersToFollow(user: String, userToFollow: String, userRepository: UserInMemoryRepository) {
        followUser.followUser(user, userToFollow)
    }
}