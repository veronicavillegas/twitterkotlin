package infraestructure.mysql

import com.nhaarman.mockitokotlin2.any
import com.twitterkata.infraestructure.DataBaseConnection
import com.twitterkata.domain.followers.repositories.FollowerMySqlRepository
import com.twitterkata.domain.users.User
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.sql.ResultSet
import kotlin.test.Ignore
import kotlin.test.assertEquals

class FollowerMySqlRepositoryShould {
    private val databaseConnection = mock(DataBaseConnection::class.java)
    private val followerMySqlRepository = FollowerMySqlRepository(databaseConnection)


    @Test
    fun generateCorrectFollowerInsertQuery() {
        val expectedQuery = givenInsertFollowerQuery()
        val userToFollow = givenUser("vero", "villegas", "@vero", "1")
        val followerUser = givenUser("maria", "paez", "@maria", "2")

        Mockito.`when`(databaseConnection.executeMySQLQuery(expectedQuery)).thenReturn(null)
        whenAddFollower(userToFollow, followerUser)

        thenExecutedQueryShouldBeQuery(expectedQuery)
    }

    @Test
    fun generateCorrectSelectAllFollowersQuery(){
        val user =  givenUser("vero", "villegas", "@vero", "1")
        val expectedQuery = givenGetFollowersOfUserQuery(user.id)

        Mockito.`when`(databaseConnection.executeMySQLQuery(expectedQuery)).thenReturn(null)
        whenGetFollowers(user)

        thenExecutedQueryShouldBeQuery(expectedQuery)
    }

    @Ignore
    @Test
    fun returnFollowersOfUser() {
        val user =  givenUser("vero", "villegas", "@vero", "1")
        val follower = givenUser("maria", "perez", "@maria", "2")
        val expectedFollowers = listOf(follower)

        val resultSet = mock(ResultSet::class.java)
        Mockito.`when`(resultSet.next()).thenReturn(true)
        Mockito.`when`(databaseConnection.executeMySQLQuery(any())).thenReturn(resultSet)

        val actualFollowers = followerMySqlRepository.getFollowersOfUser(user)

        assertEquals(expectedFollowers[0].id, actualFollowers[0].id)
    }

    private fun thenExecutedQueryShouldBeQuery(query: String) {
        Mockito.verify(databaseConnection).executeMySQLQuery(query)
    }

    private fun whenAddFollower(userToFollow: User, followerUser: User) {
        followerMySqlRepository.addFollower(userToFollow = userToFollow, followerUser = followerUser)
    }

    private fun givenUser(firstname: String, surname: String, nickname: String, id: String) = User(firstname, surname, nickname, id)

    private fun givenInsertFollowerQuery() = "INSERT INTO followers (id_user, id_follower) VALUES (1, 2)"


    private fun whenGetFollowers(user: User) {
        followerMySqlRepository.getFollowersOfUser(user)
    }

    private fun givenGetFollowersOfUserQuery(userId: String) = "SELECT id_follower FROM followers WHERE id_user = $userId"

}