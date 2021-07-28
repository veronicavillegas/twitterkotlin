package com.twitterkata

import com.twitterkata.domain.users.actions.UpdateUser
import com.twitterkata.domain.users.User

fun main() {
    println("Hello Vero")

    //Obtener la conexion y mandar a MySqlRepo
    //connectMySql()


    val updateUser = UpdateUser(RepositoryProvider.provideSQLRepository())
    //updateUser.registerUser(User("alguien2", "algo2", "@alguien2"))
    updateUser.updateUser(User("alguien", "algo4", "@alguien2"))
    //Cerrar conexiones
}