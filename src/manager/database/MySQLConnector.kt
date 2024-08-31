package com.alan.manager.database

import com.alan.manager.utils.getServerProperties
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

fun getConnection(): Connection {

    val properties = getServerProperties()

    val url = "jdbc:mysql://${properties.host}/${properties.database}"
    return DriverManager.getConnection(url, properties.user, properties.password)
}

fun executeQuery(query: String): ResultSet {
    val connection = getConnection()
    return connection.createStatement().executeQuery(query)
}