package com.twitterkata.api

import com.twitterkata.api.JsonVertxMapper
import com.twitterkata.infraestructure.database.MySqlConnection

class DatabaseInitialization {

    private val useDatabase = true

    fun getJsonMapper() = JsonVertxMapper()

    fun initDatabaseConnection() {
        if (useDatabase) {
            MySqlConnection.initConnection()
        }
    }
}