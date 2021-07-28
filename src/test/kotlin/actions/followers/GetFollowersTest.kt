package actions.followers

import com.twitterkata.domain.followers.actions.GetFollowers
import com.twitterkata.domain.followers.repositories.FollowerInMemoryRepository
import com.twitterkata.domain.users.repositories.UserInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetFollowersTest {
    private val userToFollow = "@maria"
    private val otherFollower = "@pedro"
    private val userRepository = UserInMemoryRepository()
    private val followerRepository = FollowerInMemoryRepository()
    private val getFollowers = GetFollowers(userRepository, followerRepository)
    private val user = "@vero"

    @Test
    fun userFollowSomebody_thenListOfFollowedIsOne() {
        makeUsersToFollow(user, userToFollow)
        assertEquals(1, getFollowers(userToFollow).size)
    }

    @Test
    fun whenIHaveTwoFollowers_thenSizeOfFollowersListIsTwo() {
        makeUsersToFollow(user, userToFollow)
        makeUsersToFollow(otherFollower, userToFollow)
        assertEquals(2, getFollowers(userToFollow).size)
    }

    @Test
    fun whenIWantToKnowWhoIsFollowingTo_thenListOfFollowersIsGiven() {
        makeUsersToFollow(user, userToFollow)
        assertEquals(user, getFollowers(userToFollow).get(0).nickname)
    }

    private fun makeUsersToFollow(user: String, userToFollow: String) {
        getFollowers(user, userToFollow)
    }
}