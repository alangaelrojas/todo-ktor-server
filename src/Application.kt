package com.alan

import com.alan.api.routes.userRouting
import com.alan.api.routes.exampleRouting
import com.alan.api.routes.exerciseRouting
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(
        Netty,
        port = 8090,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

@JvmOverloads
fun Application.module(testing: Boolean = false) {
    configureAuth()
    configureSerialization()
}

fun Application.configureAuth() {

    val secret = environment.config.propertyOrNull("jwt.secret")?.getString()?: ""
    val issuer = "" //environment.config.property("jwt.issuer").getString()
    val audience = "" //environment.config.property("jwt.audience").getString()
    val myRealm = "" //environment.config.property("jwt.realm").getString()

    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )

            validate { credential ->
                if (credential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else null
            }

            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }

    // Initialize routing after authentication configuration
    configureRouting()
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson()
    }
}

fun Application.configureRouting() {
    routing {
        userRouting()
        exerciseRouting()
        exampleRouting()
    }
}
