package com.alan.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import java.util.*

fun Application.generateJwt(userId: Int, username: String): String {

    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()

    var date = Date()
    val c = Calendar.getInstance().apply {
        time = date
        add(Calendar.DATE, 1)
    }
    date = c.time

    return JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("user", userId)
        .withClaim("username", username)
        .withExpiresAt(date)
        .sign(Algorithm.HMAC256(secret))
}