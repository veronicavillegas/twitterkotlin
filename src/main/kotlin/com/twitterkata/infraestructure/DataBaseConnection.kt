package com.twitterkata.infraestructure

import java.sql.ResultSet

interface DataBaseConnection {
    fun initConnection()
    fun executeMySQLQuery(query: String): ResultSet?
    fun close()
}