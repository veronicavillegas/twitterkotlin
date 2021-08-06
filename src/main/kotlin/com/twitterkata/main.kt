package com.twitterkata

import com.twitterkata.api.VertxServer
import com.twitterkata.domain.FactoryImpl

fun main() {
    VertxServer(FactoryImpl()).start()
}