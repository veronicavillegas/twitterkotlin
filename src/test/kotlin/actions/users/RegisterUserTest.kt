package actions.users

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RegisterUserTest {
    val userRepository = UserInMemoryRepository()
    private var registerUser = RegisterUser(userRepository)

    @Test
    fun registerEmptyNickname_thenInvalidNickname(){
        val result = registerUser(User("Veronica", "Villegas", ""))
        assertEquals(Status.FAIL, result.status)
        assertEquals(Messages.INVALID_NICKNAME, result.message)
    }

    @Test
    fun registerUser_thenNoError() {
        registerUser(User("Veronica", "Villegas", "@vero"))
    }

    @Test
    fun registerNicknameAlreadyUsed_thenNicknameAlreadyUsedError() {
        userRepository.save(User("Veronica", "Villegas", "@vero"))
        val resultMessage = registerUser(User("Veronica", "Villegas", "@vero"))
        assertEquals(Status.FAIL, resultMessage.status)
        assertEquals(Messages.NICKNAME_ALREADY_EXISTENT, resultMessage.message)
    }

    @Test
    fun registerUser_thenOK() {
        val response = registerUser(User("Veronica", "Villegas", "@vero"))

        assertEquals(Status.OK, response.status)
        assertEquals(Messages.OK, response.message)
    }
}