package com.alan.local.mapper

import com.alan.api.model.request.user.UserRequest
import com.alan.api.model.response.user.UserCredentialsResponse
import com.alan.manager.utils.JsonUtils
import com.finloop.core.manager.exception.UnAuthorizedException
import java.sql.ResultSet

class UserMapper {

    fun mapUserCreationResultToBoolean(result: ResultSet): UserCredentialsResponse {
        val metadata = result.metaData
        val creationFailed = (metadata.columnCount == 1)

        if (creationFailed) {
            throw UnAuthorizedException()
        }
        return mapResultSetToUserCredentialsResponse(result)
    }

    fun mapUserInformationResultToUser(result: ResultSet): UserRequest {
        val array = JsonUtils.getJsonArrayFromResultSet(result)
        val userListResult = JsonUtils.convertJsonArrayToClass(array, UserRequest::class.java)
        return userListResult.first()
    }

    fun mapUserPasswordResultToBoolean(result: ResultSet): UserCredentialsResponse {
        return mapResultSetToUserCredentialsResponse(result)
    }

    fun mapUserBySearchingResultToListUser(result: ResultSet): List<UserRequest> {
        val jsonArray = JsonUtils.getJsonArrayFromResultSet(result)
        return JsonUtils.convertJsonArrayToClass(jsonArray, UserRequest::class.java)
    }

    private fun mapResultSetToUserCredentialsResponse(result: ResultSet): UserCredentialsResponse {
        var userId = 0
        var username = ""
        var password = ""
        while (result.next()) {
            userId = result.getInt(1)
            username = result.getString(2)
            password = result.getString(3)
        }
        return UserCredentialsResponse(userId, username, password)
    }
}