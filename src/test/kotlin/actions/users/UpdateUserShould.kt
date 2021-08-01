package actions.users

import com.twitterkata.domain.users.InexistentUserException
import com.twitterkata.domain.users.User

import org.junit.jupiter.api.Test
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserRepository
import com.twitterkata.infraestructure.IDGenerator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

internal class UpdateUserShould {
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val updateUser = UpdateUser(userRepository)
    private val uuid = Mockito.mock(IDGenerator::class.java)
    private val idGenerated = "123"
    private val user = User("Veronica", "Villegas", "@vero", idGenerated)

    @BeforeEach
    fun setUp() {
        Mockito.`when`(uuid.generateId()).thenReturn(idGenerated)
    }

    @Test
    fun throwExceptionWhenUserNotExists() {
        Mockito.`when`(userRepository.get(user.nickname)).thenReturn(null)
        assertThrows<InexistentUserException> { updateUser(user)  }
    }

    @Test
    fun invokeUserRepoUpdateWhenUpdateUser() {
        val savedUser = User("maria", "perez", "@vero", idGenerated)
        Mockito.`when`(userRepository.get(user.nickname)).thenReturn(savedUser)
        updateUser(user)
        Mockito.verify(userRepository).update(user)
    }
}
