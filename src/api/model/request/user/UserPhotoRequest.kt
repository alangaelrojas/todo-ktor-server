package com.alan.api.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserPhotoRequest(
    val username: String,
    val urlPhoto: String
)
