package com.alan.local.interactors

import com.alan.api.model.response.exercise.ExerciseResponse
import com.alan.local.mapper.ExercisesMapper
import java.sql.ResultSet

class ExercisesInteractor {

    private val mapper by lazy { ExercisesMapper() }

    fun mapAllExercisesResultToExercisesResponse(result: ResultSet): List<ExerciseResponse> {
        return mapper.mapAllExercisesResultToExercisesResponse(result)
    }

}