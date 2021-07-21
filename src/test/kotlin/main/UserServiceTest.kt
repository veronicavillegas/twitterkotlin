package main

import main.dto.Dto
import main.dto.User
import main.exception.InexistentUser
import main.exception.InvalidNickname
import main.exception.NicknameAlreadyUsed

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class UserServiceTest {
    private val userService: UserService = UserService()

    @Test
    fun registerEmptyNickname_thenInvalidNickname(){
        assertFailsWith<InvalidNickname> (
            block = {
                userService.registerUser(User("Veronica", "Villegas", ""))
            })
    }

    @Test
    fun registerUser_thenNoError() {
        userService.registerUser(User("Veronica", "Villegas", "@vero"))
    }

    @Test
    fun registerUser_thenUserIsSaved() {
        userService.registerUser(User("Veronica", "Villegas", "@vero"))
        val user: User? = userService.getUser("@vero") as User

        assertEquals("Veronica", user?.firstName)
        assertEquals("Villegas", user?.surname)
        assertEquals("@vero", user?.nickname)
    }

    @Test
    fun registerNicknameAlreadyUsed_thenNicknameAlreadyUsedError() {
        userService.registerUser(User("Veronica", "Villegas", "@vero"))
        assertFailsWith<NicknameAlreadyUsed> (  block = {
            userService.registerUser(User("Veronica", "Villegas", "@vero"))
        })
    }

    @Test
    fun updateInexistentUser_thenInexistentUserError() {
        assertFailsWith<InexistentUser> (  block = {
            userService.updateUser(User("Yanina", "Rodriguez", "@vero"))
        })
    }

    @Test
    fun updateUser_thenUserIsUpdated() {
        userService.registerUser(User("Veronica", "Villegas", "@vero"))
        userService.updateUser(User("Maria", "Rodriguez", "@vero"))
        var user: User = userService.getUser("@vero") as User

        assertEquals("Maria", user.firstName)
        assertEquals("Rodriguez", user.surname)
    }

    @Test
    fun userFollowNobody_thenListOfFollowedIsZero() {
        userService.registerUser(User("Veronica", "Villegas", "@vero"))
        assertEquals(0, userService.getFollowedUsers("@vero").size)
    }

    @Test
    fun userFollowSomebody_thenListOfFollowedIsOne() {
        userService.registerUser(User("Veronica", "Villegas", "@vero"))
        userService.followUser("@vero", "@abc")
        assertEquals(1, userService.getFollowedUsers("@vero").size)
    }
}
