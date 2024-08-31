package com.alan.api.routes

import com.alan.api.interactors.UserAppInteractor
import com.alan.api.model.request.user.NewUserRequest
import com.alan.api.model.request.user.UserLoginRequest
import com.alan.api.model.request.user.UserPhotoRequest
import com.alan.auth.generateJwt
import com.alan.auth.getUsernameFromJwt
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val interactor by lazy { UserAppInteractor() }

fun Route.userRouting() {
    route("/users") {

        // authenticate("auth-jwt") {

            // Get user information by username
            get {

                val username = getUsernameFromJwt()

                if (username == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }

                val result = interactor.getUserInformation(username)
                with(result) {

                    val userInformation = entity
                    val exception = exception

                    if (exception != null) {
                        call.respond(HttpStatusCode.InternalServerError, exception.message.toString())
                        return@with
                    }

                    if (userInformation == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                        return@with
                    }

                    //call.response.header(HttpHeaders.Authorization, application.generateJwt())
                    call.respond(userInformation)
                }
            }

            // Get users by searching
            get("/search") {

                val filter = call.parameters["busqueda"].toString()

                if (filter.isEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, "Busqueda required")
                    return@get
                }

                val result = interactor.getUsersBySearching(filter)

                with(result) {

                    val users = entity
                    val exception = exception

                    if (exception != null) {
                        call.respond(HttpStatusCode.InternalServerError, exception.message.toString())
                        return@with
                    }

                    if (users == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                        return@with
                    }

                    //call.response.header(HttpHeaders.Authorization, application.generateJwt())
                    call.respond(users)
                }
            }

            // Get user information by id
            get("/{id}") {
                val candidate = call.parameters["id"]?.toLong()
            }

            // Update password
            put {
                val body = call.receive<UserLoginRequest>()
                call.respond("Updated")
            }

            // Update user photo
            put("/foto") {
                val body = call.receive<UserPhotoRequest>()
                call.respond("Updated")
            }

            // Update user firebaseUuid
            put("/firebaseId") {

            }

            delete {
            }
        // }

        // Login user
        post("/login") {
            val body = call.receive<UserLoginRequest>()
            val result = interactor.verifyPassword(body.username, body.password)
            with(result) {

                val userLogin = entity
                val exception = exception

                if (exception != null) {
                    call.respond(HttpStatusCode.InternalServerError, exception.message.toString())
                    return@with
                }

                if (userLogin == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@with
                }

                call.response.header(
                    HttpHeaders.Authorization,
                    application.generateJwt(userLogin.userId, userLogin.username)
                )
                call.respond(HttpStatusCode.OK)
            }
        }

        // Create new user
        post("/nuevo") {
            val body: NewUserRequest? = call.receive()
            body?.let {
                val result = interactor.createUser(it.email, it.nombre, it.password, it.username)
                with(result) {

                    val userLogin = entity
                    val exception = exception

                    if (exception != null) {
                        call.respond(HttpStatusCode.InternalServerError, exception.message.toString())
                        return@with
                    }

                    if (userLogin == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                        return@with
                    }

                    call.response.header(
                        HttpHeaders.Authorization,
                        application.generateJwt(userLogin.userId, userLogin.username)
                    )
                    call.respond(HttpStatusCode.OK)
                }
            } ?: call.respond(HttpStatusCode.BadRequest, "Invalid body")
        }
    }
}