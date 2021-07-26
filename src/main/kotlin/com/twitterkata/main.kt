package com.twitterkata

import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

fun main() {
    println("Hello Vero")
    connectMySql()
}


fun connectMySql() {
    val connectionProps = Properties()
    connectionProps.put("user", "root")
    connectionProps.put("password", "adminadmin")
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val conn = DriverManager.getConnection(
            "jdbc:" + "mysql" + "://" +
                    "127.0.0.1" +
                    ":" + "3306" + "/" +
                    "",
            connectionProps)
        val stmt = conn!!.createStatement()
        var resultset = stmt!!.executeQuery("SHOW DATABASES;")

        if (stmt.execute("SHOW DATABASES;")) {
            resultset = stmt.resultSet
        }

        while (resultset!!.next()) {
            println(resultset.getString("Database"))
        }
    } catch (ex: SQLException) {
        // handle any errors
        ex.printStackTrace()
    } catch (ex: Exception) {
        // handle any errors
        ex.printStackTrace()
    }
}