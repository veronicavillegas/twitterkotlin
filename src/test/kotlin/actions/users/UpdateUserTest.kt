package actions.users

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
    fun updateInexistentUser_thenInexistentUserError() {
        val result = updateUser(User("Yanina", "Rodriguez", "@inexistent"))

        assertEquals(Status.FAIL, result.status)
        assertEquals(Messages.INEXISTENT_USER, result.message)
    }

    @Test
    fun updateUser_thenUserIsUpdated() {
        updateUser(User("Veronica", "Villegas", "@vero"))
        val response = updateUser(User("Maria", "Rodriguez", "@vero"))
        assertEquals(Status.OK, response.status)
        assertEquals(Messages.OK, response.message)
    }
}
