package com.alan.local.queries

object ExercisesQueries {

    val allExercises
        get() = "call SpEjercicios(2, '', '', '', '', '', '', 0, '', '', '', '');"

    var searchExercise = ""
        set(value) {
            field = "call SpEjercicios(3, '', '', '', '', '', '', 0, '', '$value', '', '');"
        }

    var searchExerciseByAparato = ""
        set(value) {
            field = "call SpEjercicios(12, '', '', '', '', '', '', 0, '', '', '', '$value');"
        }

}
