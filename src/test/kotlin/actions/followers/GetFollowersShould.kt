package actions.followers

import com.nhaarman.mockitokotlin2.any
import com.twitterkata.domain.followers.actions.GetFollowers
import com.twitterkata.domain.followers.repositories.FollowerRepository
import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.assertEquals

class GetFollowersShould {
    private val userNickname = "@vero"
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val followerRepository = Mockito.mock(FollowerRepository::class.java)
    private val getFollowers = GetFollowers(userRepository, followerRepository)
    
    val user = User("maria", "perez", "@maria", 1)

    @Test
    fun throwErrorWhenUserNotExist(){
        Mockito.`when`(userRepository.get(userNickname)).thenReturn(null)
        assertThrows<InexistentUserException> { getFollowers(userNickname) }
    }

    @Test
    fun askFollowerRepoForFollowersOfUser() {
        Mockito.`when`(userRepository.get(userNickname)).thenReturn(user)
        Mockito.`when`(followerRepository.getFollowersOfUser(user)).thenReturn(any())
        getFollowers(userNickname)
        Mockito.verify(followerRepository).getFollowersOfUser(user)
    }

    @Test
    fun returnFollowersOfUser() {
        Mockito.`when`(userRepository.get(userNickname)).thenReturn(user)
        val expectedFollowers = listOf(User("first", "sur", "@nick"))
        Mockito.`when`(followerRepository.getFollowersOfUser(user)).thenReturn(expectedFollowers)

        val actualFollowers = getFollowers(userNickname)

        assertEquals(1, actualFollowers.size)
        assertEquals(expectedFollowers[0].firstName, actualFollowers[0].firstName)
        assertEquals(expectedFollowers[0].surname, actualFollowers[0].surname)
        assertEquals(expectedFollowers[0].nickname, actualFollowers[0].nickname)
    }
}