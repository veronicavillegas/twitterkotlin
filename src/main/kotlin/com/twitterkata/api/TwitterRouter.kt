package com.twitterkata.api

import com.twitterkata.api.handlers.*
import com.twitterkata.domain.users.actions.RegisterUser
import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.repositories.UserMySqlRepository
import com.twitterkata.infraestructure.JsonVertxUtility
import com.twitterkata.infraestructure.MySqlConnection
import com.twitterkata.infraestructure.UUIDGenerator
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class TwitterRouter(private val vertx: Vertx) {
    val kataTwitter = "/katatwitter"

    fun applyRoutes(router: Router): Router = router.apply {
        val jsonUtility = JsonVertxUtility()
        val connection = MySqlConnection()
        //connection.initConnection()
        val userRepository = UserMySqlRepository(connection)
        val registerUser = RegisterUser(userRepository = userRepository, idGenerator = UUIDGenerator())
        val updateUser = UpdateUser(userRepository)

        post("$kataTwitter/users")
            .consumes("application/json")
            .handler{ context -> RegisterUserHandler(registerUser, jsonUtility).handle(context)}
        put("$kataTwitter/users/:nickname")
            .handler{context -> UpdateUserHandler(updateUser, jsonUtility)}
        //get("$kataTwitter/users/:nickname").handler(GetUser().handle())
        //put("$kataTwitter/users/follow").handler(FollowUser())

        //post("$kataTwitter/twitt").handler(TwitMessage())
        //get("$kataTwitter/twitt/:nickname").handler(GetTwitt())
    }

}
