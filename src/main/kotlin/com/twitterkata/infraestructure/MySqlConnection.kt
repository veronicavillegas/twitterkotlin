package com.twitterkata.infraestructure

import com.twitterkata.infraestructure.DataBaseConnection
import java.sql.*
import java.util.*

class MySqlConnection: DataBaseConnection {
    private var conn: Connection? = null
    private var stmt: Statement? = null
    private var resultset: ResultSet? = null
    private val username = "root" // provide the username
    private val password = "adminadmin" // provide the corresponding password

    override fun initConnection() {
        if(conn == null) {
            getMySqlConnection()
        }
        initStatment()
    }

    override fun executeMySQLQuery(query: String): ResultSet? {
        try {
            stmt!!.executeQuery("USE twitterkata")
            if (stmt!!.execute("$query;")) {
                resultset = stmt!!.resultSet
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
        return resultset
    }

    override fun close() {
        closeResultSet()
        closeStatement()
        closeConnection()
    }

    private fun getMySqlConnection() {
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
    
    private fun initStatment() {
        if (stmt == null) {
            stmt = conn!!.createStatement()
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

    private fun closeStatement() {
        if (stmt != null) {
            try {
                stmt!!.close()
            } catch (sqlEx: SQLException) {
            }
        }
    }

    private fun closeResultSet() {
        if (resultset != null) {
            try {
                resultset!!.close()
            } catch (sqlEx: SQLException) {
            }
        }
    }
}