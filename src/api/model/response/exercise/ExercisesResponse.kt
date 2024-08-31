package com.alan.api.model.response.exercise

import kotlinx.serialization.Serializable

@Serializable
data class ExercisesResponse(
    val exercises: List<ExerciseResponse> = listOf(ExerciseResponse())
)

@Serializable
data class ExerciseResponse(
    val ejercicioId: Int = 0,
    val nombre: String = "",
    val fotoUrl: String = "",
    val descripcionCorta: String = "",
    val descripcionLarga: String = "",
    val tips: String = "",
    val ejecucion: String = "",
    val caloriasAQuemar: Int = 0,
    val parteDelCuerpoId: Int = 0,
    val nombreParteDelCuerpo: String = "",
    val aparatoId: Int = 0,
    val nombreAparato: String = "",
    val tipoAparato: String = ""
)
