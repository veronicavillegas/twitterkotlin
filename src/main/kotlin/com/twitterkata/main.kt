package com.twitterkata

import com.twitterkata.actions.user_account.UpdateUser
import com.twitterkata.infraestructure.repositories.UserMySqlRepository
import com.twitterkata.model.User
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

fun main() {
    println("Hello Vero")
    //connectMySql()
    val updateUser = UpdateUser(UserMySqlRepository())
    //updateUser.registerUser(User("alguien2", "algo2", "@alguien2"))
    updateUser.updateUser(User("alguien3", "algo3", "@alguien2"))
}