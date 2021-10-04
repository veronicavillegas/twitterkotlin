package com.twitterkata

import com.twitterkata.api.DatabaseInitialization
import com.twitterkata.api.FactoryActions
import com.twitterkata.api.VertxServer

fun main() {
    VertxServer(DatabaseInitialization(), FactoryActions()).start()
}