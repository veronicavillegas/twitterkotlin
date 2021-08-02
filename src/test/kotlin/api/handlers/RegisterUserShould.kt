package api.handlers

import com.twitterkata.api.handlers.RegisterUserHandler
import io.vertx.ext.web.RoutingContext
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.Ignore

class RegisterUserShould {
    val registerUser = RegisterUserHandler()
    val event = mock(RoutingContext::class.java)

    @Test
    fun mapCorrectRegisterUserData() {
        val registerUserData = "{\n\t\"nickname\": \"@vero\", \n\t\"surname\": \"villegas\", \n\t\"firstname\": \"vero\"\n}"
  //      Mockito.`when`(event.bodyAsString).thenReturn(registerUserData)
//        registerUser.handle(event)

    }
}