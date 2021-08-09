package com.twitterkata.api

import com.twitterkata.api.handlers.*
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class TwitterRouter(private val vertx: Vertx, private val factory: Factory) {
    val kataTwitter = "/katatwitter"

    fun applyRoutes(router: Router): Router = router.apply {
        factory.initDatabaseConnection()
        val jsonMapper = factory.getJsonMapper()
        val registerUser = factory.getRegisterUserAction()
        val updateUser = factory.getUpdateUserAction()
        val getUser = factory.getUserAction()
        val followUser = factory.getFollowUserAction()

        get("$kataTwitter/hello")
            .handler{ context -> HelloHandler().handle(context)}
        post("$kataTwitter/users")
            .consumes("application/json")
            .handler{ context -> RegisterUserHandler(registerUser, jsonMapper).handle(context)}
        put("$kataTwitter/users/:nickname/")
            .consumes("application/json")
            .handler{context -> UpdateUserHandler(updateUser, jsonMapper).handle(context)}
        get("$kataTwitter/users/:nickname")
            .handler{context -> GetUserHandler(getUser, jsonMapper).handle(context)}
        put("$kataTwitter/users/follow")
            .handler{context -> FollowUserHandler(followUser, jsonMapper).handle(context)}

        //post("$kataTwitter/twitt").handler(TwitMessage())
        //get("$kataTwitter/twitt/:nickname").handler(GetTwitt())
    }
}
