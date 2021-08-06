package com.twitterkata.api

import com.twitterkata.Factory
import com.twitterkata.api.handlers.*
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class TwitterRouter(private val vertx: Vertx, private val factory: Factory) {
    val kataTwitter = "/katatwitter"

    fun applyRoutes(router: Router): Router = router.apply {
        factory.initDatabaseConnection()
        val jsonUtility = factory.getJsonUtility()
        val registerUser = factory.getRegisterUserAction()
        val updateUser = factory.getUpdateUserAction()

        get("$kataTwitter/hello")
            .handler{ context -> HelloHandler().handle(context)}
        post("$kataTwitter/users")
            .consumes("application/json")
            .handler{ context -> RegisterUserHandler(registerUser, jsonUtility).handle(context)}
        put("$kataTwitter/users/:nickname/")
            .consumes("application/json")
            .handler{context -> UpdateUserHandler(updateUser, jsonUtility).handle(context)}
        //get("$kataTwitter/users/:nickname").handler(GetUser().handle())
        //put("$kataTwitter/users/follow").handler(FollowUser())

        //post("$kataTwitter/twitt").handler(TwitMessage())
        //get("$kataTwitter/twitt/:nickname").handler(GetTwitt())
    }
}
