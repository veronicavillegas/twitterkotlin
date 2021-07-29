package actions.users

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.actions.GetUser

import org.junit.jupiter.api.Test
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserInMemoryRepository
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.DataBaseConnection
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class UpdateUserShould {
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val updateUser = UpdateUser(userRepository)
    private val user = User("Veronica", "Villegas", "@vero")

    @Test
    fun throwExceptionWhenUserNotExists() {
        Mockito.`when`(userRepository.get(user.nickname)).thenReturn(null)
        assertThrows<InexistentUserException> { updateUser(user)  }
    }

    @Test
    fun invokeUserRepoUpdateWhenUpdateUser() {
        val savedUser = User("maria", "perez", "@vero")
        Mockito.`when`(userRepository.get(user.nickname)).thenReturn(savedUser)
        updateUser(user)
        Mockito.verify(userRepository).update(user)
    }
}
