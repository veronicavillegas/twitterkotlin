package domain.users.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.twitterkata.domain.users.User
import com.twitterkata.infraestructure.database.TwitterDataBase
import com.twitterkata.infraestructure.database.impl.Exposed
import com.twitterkata.infraestructure.mappers.UserMapper
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository
import com.twitterkata.infraestructure.mappers.impl.UserMapperExposedImpl
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockSettings
import org.mockito.Mockito

class UserMySqlRepositoryShould {
    private val twitterDatabase = Mockito.mock(Exposed::class.java)
    private val userMapper = Mockito.mock(UserMapper::class.java)
    private val userMySqlRepo = UserMySqlRepository(userMapper, twitterDatabase)

    @Test
    fun saveUser() {
        val user = User("Paula", "Ramirez", "@paula", "123")
        userMySqlRepo.save(user)
        verify(twitterDatabase).save(user)
    }

    @Test
    fun getUser() {
        val nickname = "@vero"
        userMySqlRepo.get(nickname = nickname)
        verify(twitterDatabase).getUserByNickname(nickname = nickname, userMapper)
    }

    @Test
    fun updateUser() {
        val user = User("veronica", "perez", "@vero", "123")
        userMySqlRepo.update(userData = user)
        verify(twitterDatabase).updateUser(user)
    }

}
