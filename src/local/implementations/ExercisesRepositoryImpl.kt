package com.alan.local.implementations

import com.alan.api.model.response.exercise.ExerciseResponse
import com.alan.manager.DataManager
import com.alan.local.callbacks.ExercisesRepository
import com.alan.local.interactors.ExercisesInteractor
import com.alan.local.queries.ExercisesQueries
import com.alan.manager.model.ModelResult
import java.sql.ResultSet

class ExercisesRepositoryImpl : ExercisesRepository {

    val interactor by lazy { ExercisesInteractor() }

    override suspend fun getAllExercises(): ModelResult<List<ExerciseResponse>> {
        val dataManager = object : DataManager<List<ExerciseResponse>>() {
            override suspend fun executeQuery(): String = ExercisesQueries.allExercises


            override suspend fun performOnSuccess(result: ResultSet): List<ExerciseResponse> {
                return interactor.mapAllExercisesResultToExercisesResponse(result)
            }

            override suspend fun performOnError(): List<ExerciseResponse>? = null
        }
        return dataManager.createCall()
    }
}