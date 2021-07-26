package com.twitterkata.infraestructure.repositories

import com.twitterkata.model.User
import java.sql.*
import java.util.*

class UserMySqlRepository {

    internal var conn: Connection? = null
    internal var username = "root" // provide the username
    internal var password = "adminadmin" // provide the corresponding password

    fun save(user: User) {

        // Si hay followers, guardar en tabla followers
        val values = "(${user.nickname}, ${user.firstName}, ${user.surname})"
        val query = "INSERT INTO USER (nickname, firstname, surname) VALUES $values"
        executeMySQLQuery(query)
    }

    fun get(nickname: String): User? {
        TODO("Not yet implemented")
    }

    fun update(id: String, userData: User) {
        TODO("Not yet implemented")
    }

    fun executeMySQLQuery(query: String) {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery("USE twitterkata")

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.resultSet
            }

            while (resultset!!.next()) {
                println(resultset.getString("Database"))
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                stmt = null
            }

            if (conn != null) {
                try {
                    conn!!.close()
                } catch (sqlEx: SQLException) {
                }

                conn = null
            }
        }
    }

    /**
     * This method makes a connection to MySQL Server
     * In this example, MySQL Server is running in the local host (so 127.0.0.1)
     * at the standard port 3306
     */
    fun getConnection() {
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

}