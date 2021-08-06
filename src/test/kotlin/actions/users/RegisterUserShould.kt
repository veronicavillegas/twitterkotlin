package actions.users

import com.twitterkata.domain.users.exceptions.InvalidNicknameException
import com.twitterkata.domain.users.exceptions.NicknameAlreadyUsedException
import com.twitterkata.domain.users.requestData.RegisterUserData
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.repositories.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RegisterUserShould {
    val userRepository = mock(UserRepository::class.java)
    private val registerUser = RegisterUser(userRepository)
    private val registerData = RegisterUserData("Veronica", "Villegas", "@vero")


    @Test
    fun throwExceptionWhenInvalidNickname(){
        assertThrows<InvalidNicknameException> {
            registerUser(RegisterUserData("Veronica", "Villegas", ""))
        }
    }

    @Test
    fun throwExceptionWhenNicknameAlreadyUsed() {
        Mockito.`when`(userRepository.get(registerData.nickname)).thenReturn(User("a", "b", "@vero", "123"))

        assertThrows<NicknameAlreadyUsedException> {
            registerUser(registerData)
        }
    }

    @Test
    fun invokeSaveToUserRepoWhenRegisterUser(){
        registerUser(registerData)
        Mockito.verify(userRepository).save(registerDataToUser(registerData))
    }

    private fun registerDataToUser(userToRegisterUser: RegisterUserData): User {
        return User(userToRegisterUser.firstname, userToRegisterUser.surname, userToRegisterUser.nickname, "")
    }
}