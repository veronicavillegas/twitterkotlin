package com.twitterkata

import com.twitterkata.actions.user_account.UpdateUser
import com.twitterkata.infraestructure.repositories.mysql.MySqlConnection
import com.twitterkata.infraestructure.repositories.mysql.UserMySqlRepository
import com.twitterkata.model.User

fun main() {
    println("Hello Vero")

    //Obtener la conexion y mandar a MySqlRepo
    //connectMySql()
    val mySqlConnection = MySqlConnection()
    mySqlConnection.initConnection()
    val updateUser = UpdateUser(UserMySqlRepository(mySqlConnection))
    //updateUser.registerUser(User("alguien2", "algo2", "@alguien2"))
    updateUser.updateUser(User("alguien", "algo4", "@alguien2"))
    //Cerrar conexiones
    mySqlConnection.close()
}