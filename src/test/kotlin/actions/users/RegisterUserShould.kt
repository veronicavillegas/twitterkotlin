package actions.users

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.InvalidNicknameException
import com.twitterkata.domain.users.NicknameAlreadyUsedException
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.repositories.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.assertEquals

class RegisterUserShould {
    val userRepository = mock(UserRepository::class.java)
    private val registerUser = RegisterUser(userRepository)
    private val userToRegister = User("Veronica", "Villegas", "@vero")

    @Test
    fun whenInvalidNicknameThrowException(){
        assertThrows<InvalidNicknameException> {
            registerUser(User("Veronica", "Villegas", ""))
        }
    }

    @Test
    fun registerNicknameAlreadyUsed_thenNicknameAlreadyUsedError() {
        Mockito.`when`(userRepository.get(userToRegister.nickname)).thenReturn(User("a", "b", "@vero"))

        assertThrows<NicknameAlreadyUsedException> {
            registerUser(userToRegister)
        }
    }

    @Test
    fun whenRegisterUser_thenInvokeSaveToUserRepo(){
        registerUser(userToRegister)
        Mockito.verify(userRepository).save(userToRegister)
    }
}