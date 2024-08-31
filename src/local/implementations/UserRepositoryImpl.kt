package com.alan.local.implementations

import com.alan.api.model.request.user.UserRequest
import com.alan.api.model.response.user.UserCredentialsResponse
import com.alan.manager.DataManager
import com.alan.local.callbacks.UserRepository
import com.alan.local.interactors.UserInteractor
import com.alan.local.queries.UserQueries
import com.alan.manager.model.ModelResult
import java.sql.ResultSet

class UserRepositoryImpl : UserRepository {

    private val interactor by lazy { UserInteractor() }

    override suspend fun verifyPassword(username: String, password: String): ModelResult<UserCredentialsResponse> {
        val dataManager = object : DataManager<UserCredentialsResponse>() {
            override suspend fun executeQuery(): String = UserQueries.getUserPassword(username)

            override suspend fun performOnSuccess(result: ResultSet): UserCredentialsResponse {
                return interactor.mapUserPasswordResultToBoolean(result, password)
            }

            override suspend fun performOnError(): UserCredentialsResponse? = null
        }
        return dataManager.createCall()
    }

    override suspend fun getUserInformation(username: String): ModelResult<UserRequest> {
        val dataManager = object : DataManager<UserRequest>() {
            override suspend fun executeQuery(): String = UserQueries.getUserInfo(username)

            override suspend fun performOnSuccess(result: ResultSet): UserRequest {
                return interactor.mapUserInformationResultToUser(result)
            }

            override suspend fun performOnError(): UserRequest? = null
        }
        return dataManager.createCall()
    }

    override suspend fun createUser(
        email: String,
        nombre: String,
        password: String,
        username: String
    ): ModelResult<UserCredentialsResponse> {
        val manager = object : DataManager<UserCredentialsResponse>() {
            override suspend fun executeQuery(): String = UserQueries.createUser(email, nombre, password, username)

            override suspend fun performOnSuccess(result: ResultSet): UserCredentialsResponse {
                return interactor.mapUserCreationResultToBoolean(result)
            }

            override suspend fun performOnError(): UserCredentialsResponse? = null
        }
        return manager.createCall()
    }

    override suspend fun getUsersBySearching(filter: String): ModelResult<List<UserRequest>> {
        val manager = object : DataManager<List<UserRequest>>() {
            override suspend fun executeQuery(): String = UserQueries.getUserBySearching(filter)

            override suspend fun performOnSuccess(result: ResultSet): List<UserRequest> {
                return interactor.mapUserBySearchingResultToListUser(result)
            }

            override suspend fun performOnError(): List<UserRequest>? = null
        }
        return manager.createCall()
    }
}