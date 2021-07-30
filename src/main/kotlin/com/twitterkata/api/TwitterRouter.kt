package com.twitterkata.api

import com.twitterkata.api.handlers.*
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class TwitterRouter(private val vertx: Vertx) {
    val kataTwitter = "/katatwitter"
    val router = Router.router(vertx)

    operator fun invoke(): Router = router.apply {
        post("$kataTwitter/users").handler(RegisterUser())
        get("$kataTwitter/users/:nickname").handler(GetUser())
        put("$kataTwitter/users").handler(UpdateUser())
        put("$kataTwitter/users/follow").handler(FollowUser())

        post("$kataTwitter/twitt").handler(TwitMessage())
        get("$kataTwitter/twitt/:nickname").handler(GetTwitt())
    }

}
