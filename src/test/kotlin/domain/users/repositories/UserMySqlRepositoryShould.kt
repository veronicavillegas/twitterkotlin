package domain.users.repositories

import com.nhaarman.mockitokotlin2.verify
import com.twitterkata.domain.users.User
import com.twitterkata.infraestructure.database.impl.ExposedDataBase
import com.twitterkata.infraestructure.mappers.UserMapper
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserMySqlRepositoryShould {
    private val twitterDatabase = Mockito.mock(ExposedDataBase::class.java)
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
