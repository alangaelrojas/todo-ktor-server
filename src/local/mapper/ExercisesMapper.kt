package com.alan.local.mapper

import com.alan.api.model.response.exercise.ExerciseResponse
import com.alan.manager.utils.JsonUtils
import java.sql.ResultSet

class ExercisesMapper {

    fun mapAllExercisesResultToExercisesResponse(result: ResultSet): List<ExerciseResponse> {
        val jsonArray = JsonUtils.getJsonArrayFromResultSet(result)
        return JsonUtils.convertJsonArrayToClass(jsonArray, ExerciseResponse::class.java)
    }

}