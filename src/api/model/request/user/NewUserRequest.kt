package com.alan.api.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class NewUserRequest(
    val username: String,
    val nombre: String,
    val password: String,
    val email: String
)
