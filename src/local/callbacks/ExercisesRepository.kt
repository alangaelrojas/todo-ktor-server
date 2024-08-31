package com.alan.local.callbacks

import com.alan.api.model.response.exercise.ExerciseResponse
import com.alan.api.model.response.exercise.ExercisesResponse
import com.alan.manager.model.ModelResult

interface ExercisesRepository {

    suspend fun getAllExercises(): ModelResult<List<ExerciseResponse>>

}