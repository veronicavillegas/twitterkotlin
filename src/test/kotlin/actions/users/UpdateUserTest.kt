package actions.users

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.actions.GetUser

import org.junit.jupiter.api.Test
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserInMemoryRepository
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.DataBaseConnection
import org.mockito.Mockito
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class UpdateUserTest {
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val updateUser = UpdateUser(userRepository)
    private val user = User("Veronica", "Villegas", "@vero")

    @Test
    fun updateInexistentUser_thenInexistentUserError() {
        Mockito.`when`(userRepository.get(user.nickname)).thenReturn(null)
        val result = updateUser(user)
        assertEquals(Status.FAIL, result.status)
        assertEquals(Messages.INEXISTENT_USER, result.message)
    }

    @Test
    fun updateUser_thenUserIsUpdated() {
        val savedUser = User("maria", "perez", "@vero")
        Mockito.`when`(userRepository.get(user.nickname)).thenReturn(savedUser)
        val response = updateUser(user)
        assertEquals(Status.OK, response.status)
        assertEquals(Messages.OK, response.message)
    }
}
