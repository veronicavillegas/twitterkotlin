package actions

import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.followers.repositories.FollowerInMemoryRepository
import com.twitterkata.domain.users.repositories.UserInMemoryRepository
import com.twitterkata.domain.users.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FollowUserTest {
    private val userRepository = UserInMemoryRepository()
    private val followerRepository = FollowerInMemoryRepository()
    private val followUser = FollowUser(userRepository, followerRepository)
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
    fun whenFollowerNotExists_thenNoUpdate() {
        followUser.followUser("@noexiste", "@maria")
        assertEquals(0,  followUser.getFollowers("@noexiste").size)
    }
    @Test
    fun userFollowNobody_thenListOfFollowedIsZero() {
        assertEquals(0, followUser(user).size)
    }

    @Test
    fun userFollowSomebody_thenListOfFollowedIsOne() {
        makeUsersToFollow(user, userToFollow)
        assertEquals(1, followUser.getFollowers(userToFollow).size)
    }

    @Test
    fun whenIHaveTwoFollowers_thenSizeOfFollowersListIsTwo() {
        makeUsersToFollow(user, userToFollow)
        makeUsersToFollow(otherFollower, userToFollow)
        assertEquals(2, followUser.getFollowers(userToFollow).size)
    }

    @Test
    fun whenIWantToKnowWhoIsFollowingTo_thenListOfFollowersIsGiven() {
        makeUsersToFollow(user, userToFollow)
        assertEquals(user, followUser.getFollowers(userToFollow).get(0).nickname)
    }

    private fun makeUsersToFollow(user: String, userToFollow: String) {
        followUser.followUser(user, userToFollow)
    }
}