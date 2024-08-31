package com.alan.manager.model

import com.alan.manager.exception.NoInternetConnectionException

data class ModelResult<Result>(
    var exception: Exception? = null,
    var entity: Result? = null
) {
    fun isNetworkAvailable() = exception !is NoInternetConnectionException

    @Throws(Exception::class)
    fun getEntityOrThrowException() = entity ?: throw exception ?: Exception()
}