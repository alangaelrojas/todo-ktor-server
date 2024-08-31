package com.alan.api.interactors

import com.alan.api.model.request.user.UserRequest
import com.alan.api.model.response.user.UserCredentialsResponse
import com.alan.local.implementations.UserRepositoryImpl
import com.alan.manager.model.ModelResult

class UserAppInteractor {

    private val repository by lazy { UserRepositoryImpl() }

    suspend fun verifyPassword(username: String, password: String): ModelResult<UserCredentialsResponse> {
        return repository.verifyPassword(username, password)
    }

    suspend fun getUserInformation(username: String): ModelResult<UserRequest> {
        return repository.getUserInformation(username)
    }

    suspend fun createUser(
        email: String,
        nombre: String,
        password: String,
        username: String
    ): ModelResult<UserCredentialsResponse> {
        return repository.createUser(email, nombre, password, username)
    }

    suspend fun getUsersBySearching(filter: String): ModelResult<List<UserRequest>> {
        return repository.getUsersBySearching(filter)
    }

}