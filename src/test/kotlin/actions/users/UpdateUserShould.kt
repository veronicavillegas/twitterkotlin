package actions.users

import com.twitterkata.domain.users.requestData.UpdateUserData
import com.twitterkata.domain.users.exceptions.InexistentUserException
import com.twitterkata.domain.users.User

import org.junit.jupiter.api.Test
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

internal class UpdateUserShould {
    private val userRepository = Mockito.mock(UserRepository::class.java)
    private val updateUser = UpdateUser(userRepository)
    private val idGenerated = "123"
    private val nickname = "@vero"
    private val updateUserData = UpdateUserData("Veronica", "Villegas")
    private val userToUpdate = User("Veronica", "Villegas", nickname, idGenerated)

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
