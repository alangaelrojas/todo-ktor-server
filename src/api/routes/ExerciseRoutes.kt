package com.alan.api.routes

import com.alan.api.interactors.ExerciseApiInteractor
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get

private val interactor by lazy { ExerciseApiInteractor() }

fun Route.exerciseRouting() {
    route("/ejercicios") {
        // Get all exercises
        get {
            val result = interactor.getExercises()
            result.entity?.let {
                call.respond(it)
            }
            result.exception?.let {
                call.respond(HttpStatusCode.NotFound, "Error  $it")
            }
        }

        // Get exercises by searching
        get("/busqueda") {

        }

        // Get exercises by equipment
        get("/aparato") {

        }

        // Get exercises by body parts
        get("/parteDelCuerpo") {

        }

        // Create a new exercise
        post("/nuevo") {

        }

        // Update exercise name
        put("/nombre") {}

        // Update exercise photo
        put("/foto") {

        }

        // Update exercise short description
        put("/descripcioncorta") {}

        // Update exercise large description
        put("/descripcionlarga") {}

        // Update exercise tip
        put("/tips") {}

        // Update exercise execution
        put("/ejecucion") {}

        // Update exercise calories
        put("/calorias") {}

        // Delete exercise
        delete("/eliminar") {}
    }
}