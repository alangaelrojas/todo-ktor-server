package com.alan.local.callbacks

import com.alan.api.model.request.user.UserRequest
import com.alan.api.model.response.user.UserCredentialsResponse
import com.alan.manager.model.ModelResult

interface UserRepository {

    suspend fun verifyPassword(username: String, password: String): ModelResult<UserCredentialsResponse>
    suspend fun getUserInformation(username: String): ModelResult<UserRequest>
    suspend fun createUser(email: String, nombre: String, password: String, username: String): ModelResult<UserCredentialsResponse>
    suspend fun getUsersBySearching(filter: String): ModelResult<List<UserRequest>>

}