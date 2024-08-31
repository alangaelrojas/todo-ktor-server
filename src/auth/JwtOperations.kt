package com.alan.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.getUsernameFromJwt(): String? {
    val principal = call.principal<JWTPrincipal>()
    return principal?.payload?.getClaim("username")?.asString()
}