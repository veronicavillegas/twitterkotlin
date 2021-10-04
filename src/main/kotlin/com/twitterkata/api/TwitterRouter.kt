package com.twitterkata.api

import com.twitterkata.api.handlers.*
import io.vertx.ext.web.Router

class TwitterRouter(private val databaseInit: DatabaseInitialization, private val factoryAction: FactoryActions) {
    private val kataTwitter = "/katatwitter"

    fun applyRoutes(router: Router): Router = router.apply {
        databaseInit.initDatabaseConnection()
        val jsonMapper = databaseInit.getJsonMapper()

        val registerUser = factoryAction.getRegisterUserAction()
        val updateUser = factoryAction.getUpdateUserAction()
        val getUser = factoryAction.getUserAction()
        val followUser = factoryAction.getFollowUserAction()

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
