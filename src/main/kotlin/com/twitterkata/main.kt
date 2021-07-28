package com.twitterkata

import com.twitterkata.actions.user_account.UpdateUser
import com.twitterkata.infraestructure.MySqlConnection
import com.twitterkata.infraestructure.repositories.user.UserMySqlRepository
import com.twitterkata.model.User

fun main() {
    println("Hello Vero")

    //Obtener la conexion y mandar a MySqlRepo
    //connectMySql()


    val updateUser = UpdateUser(RepositoryProvider.provideSQLRepository())
    //updateUser.registerUser(User("alguien2", "algo2", "@alguien2"))
    updateUser.updateUser(User("alguien", "algo4", "@alguien2"))
    //Cerrar conexiones
}