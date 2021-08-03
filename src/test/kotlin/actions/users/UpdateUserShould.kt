package actions.users

import com.twitterkata.domain.UpdateUserData
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
    private val nickname = "@vero"
    private val updateUserData = UpdateUserData("Veronica", "Villegas")
    private val userToUpdate = User("Veronica", "Villegas", nickname, idGenerated)
    @BeforeEach
    fun setUp() {
        Mockito.`when`(uuid.generateId()).thenReturn(idGenerated)
    }

    @Test
    fun throwExceptionWhenUserNotExists() {
        Mockito.`when`(userRepository.get(nickname)).thenReturn(null)
        assertThrows<InexistentUserException> { updateUser(nickname, updateUserData)  }
    }

    @Test
    fun invokeUserRepoUpdateWhenUpdateUser() {
        val savedUser = User("maria", "perez", "@vero", idGenerated)
        Mockito.`when`(userRepository.get(nickname)).thenReturn(savedUser)
        updateUser(nickname, updateUserData)
        Mockito.verify(userRepository).update(userToUpdate)
    }
}
