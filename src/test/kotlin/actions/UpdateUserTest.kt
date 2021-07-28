package actions

import com.twitterkata.domain.enums.Messages
import com.twitterkata.domain.enums.Status
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.actions.GetUser

import org.junit.jupiter.api.Test
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserInMemoryRepository
import kotlin.test.assertEquals

internal class UpdateUserTest {
    private var updateUser = UpdateUser(UserInMemoryRepository())
    @Test
    fun registerEmptyNickname_thenInvalidNickname(){
        val result = updateUser.registerUser(User("Veronica", "Villegas", ""))
        assertEquals(Status.FAIL, result.status)
        assertEquals(Messages.INVALID_NICKNAME, result.message)
    }

    @Test
    fun registerUser_thenNoError() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
    }

    @Test
    fun registerNicknameAlreadyUsed_thenNicknameAlreadyUsedError() {
        val userRepository = UserInMemoryRepository()
        userRepository.save(User("Veronica", "Villegas", "@vero"))
        updateUser = UpdateUser(userRepository)

        val resultMessage = updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        assertEquals(Status.FAIL, resultMessage.status)
        assertEquals(Messages.NICKNAME_ALREADY_EXISTENT, resultMessage.message)
    }

    @Test
    fun registerUser_thenOK() {
        val response = updateUser.registerUser(User("Veronica", "Villegas", "@vero"))

        assertEquals(Status.OK, response.status)
        assertEquals(Messages.OK, response.message)
    }

    @Test
    fun updateInexistentUser_thenInexistentUserError() {
        val result = updateUser.updateUser(User("Yanina", "Rodriguez", "@inexistent"))

        assertEquals(Status.FAIL, result.status)
        assertEquals(Messages.INEXISTENT_USER, result.message)
    }

    @Test
    fun updateUser_thenUserIsUpdated() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        val response = updateUser.updateUser(User("Maria", "Rodriguez", "@vero"))
        assertEquals(Status.OK, response.status)
        assertEquals(Messages.OK, response.message)
    }
}
