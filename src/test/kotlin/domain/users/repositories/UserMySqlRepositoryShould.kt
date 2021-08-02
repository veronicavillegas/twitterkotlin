package domain.users.repositories

import com.nhaarman.mockitokotlin2.verify
import com.twitterkata.domain.users.User
import com.twitterkata.domain.users.repositories.UserMySqlRepository
import com.twitterkata.infraestructure.DataBaseConnection
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserMySqlRepositoryShould {
    val dataBaseConnection = Mockito.mock(DataBaseConnection::class.java)
    val userMySqlRepository = UserMySqlRepository(connection = dataBaseConnection)

    @Test
    fun saveUser() {
        val user = User("Paula", "Ramirez", "@paula", "123")
        val query = givenInsertQuery(user)
        Mockito.`when`(dataBaseConnection.executeMySQLQuery(query)).thenReturn(null)

        userMySqlRepository.save(user)

        verify(dataBaseConnection).executeMySQLQuery(query)
    }

    private fun givenInsertQuery(user: User): String {
        val values = "('${user.nickname}', '${user.firstName}', '${user.surname}')"
        val query = "INSERT INTO users (nickname, firstname, surname) VALUES $values"
        return query
    }
}
