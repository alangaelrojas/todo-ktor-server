package com.alan.manager.utils

import java.io.FileInputStream
import java.io.InputStream
import java.util.*

data class ServerProperties(val host: String, val database: String, val user: String, val password: String)

fun getServerProperties(): ServerProperties {

    val prop = Properties()
    val input: InputStream = FileInputStream("server.properties")

    // load a properties file
    prop.load(input)

    val hostProperty = prop.getProperty("host")
    val databaseProperty = prop.getProperty("database")
    val userProperty = prop.getProperty("user")
    val passwordProperty = prop.getProperty("password")

    return ServerProperties(hostProperty, databaseProperty, userProperty, passwordProperty)
}