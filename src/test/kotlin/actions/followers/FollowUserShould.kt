package actions.followers

import com.twitterkata.domain.followers.actions.FollowUser
import com.twitterkata.domain.followers.repositories.FollowerRepository
import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock

class FollowUserShould {
    private val userRepository = mock(UserRepository::class.java)
    private val followerRepository = mock(FollowerRepository::class.java)
    private val followUser = FollowUser(userRepository, followerRepository)

    private val userToFollow = User("pedro", "pe", "@pedro", 0)
    private val followerUser = User("maria", "ma", "@maria", 0)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun throwExceptionWhenUserNotExists() {

        Mockito.`when`(userRepository.get("@maria")).thenReturn(null)
        Mockito.`when`(userRepository.get("@pedro")).thenReturn(userToFollow)

        assertThrows<InexistentUserException> {
            followUser("@maria", "@pedro")
        }
    }

    @Test
    fun throwExceptionWhenUserToFollowNotExists() {
        Mockito.`when`(userRepository.get("@maria")).thenReturn(followerUser)
        Mockito.`when`(userRepository.get("@pedro")).thenReturn(null)

        assertThrows<InexistentUserException> {
            followUser("@maria", "@pedro")
        }
    }

    @Test
    fun askToFollowerRepoToAddFollower() {
        Mockito.`when`(userRepository.get("@maria")).thenReturn(followerUser)
        Mockito.`when`(userRepository.get("@pedro")).thenReturn(userToFollow)

        followUser("@maria", "@pedro")
        Mockito.verify(followerRepository).addFollower(userToFollow, followerUser)
    }

}