package com.alan.api.interactors

import com.alan.api.model.response.exercise.ExerciseResponse
import com.alan.api.model.response.exercise.ExercisesResponse
import com.alan.local.implementations.ExercisesRepositoryImpl
import com.alan.local.implementations.UserRepositoryImpl
import com.alan.manager.model.ModelResult

class ExerciseApiInteractor {

    private val repository by lazy { ExercisesRepositoryImpl() }

    suspend fun getExercises(): ModelResult<List<ExerciseResponse>> {
        return repository.getAllExercises()
    }

}