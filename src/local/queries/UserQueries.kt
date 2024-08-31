package com.alan.local.queries

object UserQueries {

    fun getUserPassword(username: String) = "call SpUsuarios(9, '${username}','', '', '', '', '', '', '');"

    fun getUserInfo(username: String) = "call SpUsuarios(2, '$username','', '', '', '', '', '', '');"

    fun createUser(
        email: String,
        nombre: String,
        password: String,
        username: String
    ) = "call SpUsuarios(1, '$username', '$nombre', '', '$email', '$password', '', '', '');"

    fun getUserBySearching(filter: String): String = "call SpUsuarios(3, '', '', '', '', '', '', '', '$filter');"

}