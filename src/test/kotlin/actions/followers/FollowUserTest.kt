package actions.followers

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

    @BeforeEach
    fun setUp() {
        userRepository.save(User("Veronica", "Villegas", "@vero"))
        userRepository.save(User("Maria", "Perez", "@maria"))
        userRepository.save(User("Pedro", "Rodriguez", "@pedro"))
    }

    @Test
    fun whenFollowerNotExists_thenNoUpdate() {
        followUser("@noexiste", "@maria")
        assertEquals(0,  followUser("@noexiste").size)
    }
    @Test
    fun userFollowNobody_thenListOfFollowedIsZero() {
        assertEquals(0, followUser(user).size)
    }

}