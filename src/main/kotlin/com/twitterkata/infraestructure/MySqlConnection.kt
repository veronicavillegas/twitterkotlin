package com.twitterkata.infraestructure

import java.sql.*
import java.util.*

class MySqlConnection {
    internal var conn: Connection? = null
    internal var username = "root" // provide the username
    internal var password = "adminadmin" // provide the corresponding password

    fun executeMySQLQuery(query: String): ResultSet? {
        getConnection()
        var stmt: Statement? = null
        var resultset: ResultSet? = null
        try {
            stmt = conn!!.createStatement()
            stmt!!.executeQuery("USE twitterkata")

            if (stmt.execute("$query;")) {
                resultset = stmt.resultSet
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            closeResultSet(resultset)
            closeStatement(stmt)
            closeConnection()
            conn = null
        }
        return resultset
    }

    private fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                "jdbc:" + "mysql" + "://" +
                        "127.0.0.1" +
                        ":" + "3306" + "/" +
                        "",
                connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }


    private fun closeConnection() {
        if (conn != null) {
            try {
                conn!!.close()
            } catch (sqlEx: SQLException) {
            }
        }
    }

    private fun closeStatement(stmt: Statement?) {
        if (stmt != null) {
            try {
                stmt.close()
            } catch (sqlEx: SQLException) {
            }
        }
    }

    private fun closeResultSet(resultset: ResultSet?) {
        if (resultset != null) {
            try {
                resultset.close()
            } catch (sqlEx: SQLException) {
            }
        }
    }
}