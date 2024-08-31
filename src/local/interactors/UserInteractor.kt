package com.alan.local.interactors

import com.alan.api.model.request.user.UserRequest
import com.alan.api.model.response.user.UserCredentialsResponse
import com.alan.local.mapper.UserMapper
import com.finloop.core.manager.exception.UnAuthorizedException
import java.sql.ResultSet

class UserInteractor {

    private val mapper by lazy { UserMapper() }

    fun mapUserCreationResultToBoolean(result: ResultSet): UserCredentialsResponse {
        return mapper.mapUserCreationResultToBoolean(result)
    }

    fun mapUserInformationResultToUser(result: ResultSet): UserRequest {
        return mapper.mapUserInformationResultToUser(result)
    }

    fun mapUserPasswordResultToBoolean(result: ResultSet, password: String): UserCredentialsResponse {
        val userLogin = mapper.mapUserPasswordResultToBoolean(result)
        val passwordMapped = userLogin.password
        if (password != passwordMapped) {
            throw UnAuthorizedException()
        }
        return userLogin
    }

    fun mapUserBySearchingResultToListUser(result: ResultSet): List<UserRequest> {
        return mapper.mapUserBySearchingResultToListUser(result)
    }

}