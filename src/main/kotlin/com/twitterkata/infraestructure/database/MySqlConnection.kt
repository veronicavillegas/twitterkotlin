package com.twitterkata.infraestructure.database

import com.twitterkata.domain.DataBaseConnection
import com.twitterkata.infraestructure.database.tables.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class MySqlConnection: DataBaseConnection {
    private var connection: Database? = null
    private val username = "root" // provide the username
    private val password = "adminadmin" // provide the corresponding password

    override fun initConnection() {
        if(connection == null) {
            getMySqlConnection()
        }
    }

    private fun getMySqlConnection() {
        connection = Database.connect("jdbc:mysql://localhost:3306/twitterkata", driver = "com.mysql.jdbc.Driver",
            user = username, password = password)
        createTables()
    }

    private fun createTables() {
        transaction {
            SchemaUtils.create(UsersTable)
        }
    }
}