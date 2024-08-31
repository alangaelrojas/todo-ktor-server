package com.alan.api.model.request.user

data class UserRequest(
    val userId: Int = 0,
    val username: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val email: String = "",
    val urlFoto: String = "",
    val firebaseTokenId: String = ""
)
