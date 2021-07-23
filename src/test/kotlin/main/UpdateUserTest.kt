package main

import com.twitterkata.actions.user_account.enums.Messages
import com.twitterkata.actions.user_account.enums.Status
import com.twitterkata.model.User

import org.junit.jupiter.api.Test
import com.twitterkata.actions.user_account.UpdateUser
import com.twitterkata.infraestructure.repositories.UserRepository
import kotlin.test.assertEquals

internal class UpdateUserTest {
    private var updateUser = UpdateUser(UserRepository())

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
        val userRepository = UserRepository()
        userRepository.save(User("Veronica", "Villegas", "@vero"))
        updateUser = UpdateUser(userRepository)

        val resultMessage = updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        assertEquals(Status.FAIL, resultMessage.status)
        assertEquals(Messages.NICKNAME_ALREADY_EXISTENT, resultMessage.message)
    }

    @Test
    fun registerUser_thenUserIsSaved() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        val user: User? = updateUser.getUser("@vero")

        assertEquals("Veronica", user?.firstName)
        assertEquals("Villegas", user?.surname)
        assertEquals("@vero", user?.nickname)
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
        updateUser.updateUser(User("Maria", "Rodriguez", "@vero"))
        val user: User? = updateUser.getUser("@vero")

        assertEquals("Maria", user?.firstName)
        assertEquals("Rodriguez", user?.surname)
    }
}
