package com.alan.api.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.exampleRouting() {

    post("/test") {
        val text: String = call.parameters["text"].toString()
        val valueMapped = mutableMapOf<String, String>()
        valueMapped["text"] = text
        call.respond(valueMapped)
    }

    get("/hello") {
        val principal = call.principal<JWTPrincipal>()
        val username = principal?.payload?.getClaim("username")?.asString()
        val expiresAt = principal?.payload?.expiresAt?.time?.minus(System.currentTimeMillis())
        call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
    }
}