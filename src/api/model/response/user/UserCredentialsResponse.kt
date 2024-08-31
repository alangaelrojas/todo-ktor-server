package com.alan.api.model.response.user

data class UserCredentialsResponse(
    val userId: Int,
    val username: String,
    val password: String
)
