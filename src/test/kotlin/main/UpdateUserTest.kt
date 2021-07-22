package main

import com.twitterkata.model.User
import com.twitterkata.actions.user_account.exceptions.InexistentUser
import com.twitterkata.actions.user_account.exceptions.InvalidNickname
import com.twitterkata.actions.user_account.exceptions.NicknameAlreadyUsed

import org.junit.jupiter.api.Test
import com.twitterkata.actions.user_account.UpdateUser
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class UpdateUserTest {
    private lateinit var updateUser: UpdateUser

    @BeforeEach
    fun setUp() {
        updateUser = UpdateUser()
    }

    @Test
    fun registerEmptyNickname_thenInvalidNickname(){
        assertFailsWith<InvalidNickname> (
            block = {
                updateUser.registerUser(User("Veronica", "Villegas", ""))
            })
    }

    @Test
    fun registerUser_thenNoError() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
    }

    @Test
    fun registerUser_thenUserIsSaved() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        val user: User? = updateUser.getUser("@vero") as User

        assertEquals("Veronica", user?.firstName)
        assertEquals("Villegas", user?.surname)
        assertEquals("@vero", user?.nickname)
    }

    @Test
    fun registerNicknameAlreadyUsed_thenNicknameAlreadyUsedError() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        assertFailsWith<NicknameAlreadyUsed> (  block = {
            updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        })
    }

    @Test
    fun updateInexistentUser_thenInexistentUserError() {
        assertFailsWith<InexistentUser> (  block = {
            updateUser.updateUser(User("Yanina", "Rodriguez", "@vero"))
        })
    }

    @Test
    fun updateUser_thenUserIsUpdated() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        updateUser.updateUser(User("Maria", "Rodriguez", "@vero"))
        var user: User = updateUser.getUser("@vero") as User

        assertEquals("Maria", user.firstName)
        assertEquals("Rodriguez", user.surname)
    }

    @Test
    fun userFollowNobody_thenListOfFollowedIsZero() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        assertEquals(0, updateUser.getFollowedUsers("@vero").size)
    }

    @Test
    fun userFollowSomebody_thenListOfFollowedIsOne() {
        updateUser.registerUser(User("Veronica", "Villegas", "@vero"))
        updateUser.followUser("@vero", "@abc")
        assertEquals(1, updateUser.getFollowedUsers("@vero").size)
    }
}
