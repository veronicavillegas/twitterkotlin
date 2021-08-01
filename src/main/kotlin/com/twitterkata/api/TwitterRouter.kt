package com.twitterkata.api

import com.twitterkata.api.handlers.*
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class TwitterRouter(private val vertx: Vertx) {
    val kataTwitter = "/katatwitter"

    fun applyRoutes(router: Router): Router = router.apply {
        post("$kataTwitter/users")
            .consumes("application/json")
            .handler{ context -> RegisterUserHandler().handle(context)}
        //get("$kataTwitter/users/:nickname").handler(GetUser().handle())
        //put("$kataTwitter/users").handler(UpdateUser())
        //put("$kataTwitter/users/follow").handler(FollowUser())

        //post("$kataTwitter/twitt").handler(TwitMessage())
        //get("$kataTwitter/twitt/:nickname").handler(GetTwitt())
    }

}
