package com.twitterkata.api

import com.twitterkata.api.handlers.*
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj

object Routes {
    val kataTwitter = "/katatwitter"
    
    operator fun invoke(vertx: Vertx): Router = Router.router(vertx).apply {
        post("$kataTwitter/users").handler(RegisterUser())
        get("$kataTwitter/users/:nickname").handler(GetUser())
        put("$kataTwitter/users").handler(UpdateUser())
        put("$kataTwitter/users/follow").handler(FollowUser())

        post("$kataTwitter/twitt").handler(TwitMessage())
        get("$kataTwitter/twitt/:nickname").handler(GetTwitt())
    }

}
