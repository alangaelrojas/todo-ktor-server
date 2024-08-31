package com.alan.manager

import com.alan.manager.model.ModelResult
import com.finloop.core.manager.exception.ConflictException
import kotlinx.coroutines.coroutineScope
import java.sql.ResultSet

abstract class DataManager<Result> {

    suspend fun createCall(): ModelResult<Result> = coroutineScope {
        try {
            val query = executeQuery()
            val result = retrieveData(query)
            onRetrieveDataSuccess(result)
        } catch (t: Exception) {
            println("CreateCall error: ${t.printStackTrace()}")
            ModelResult(t, onRetrieveDataError())
        }
    }

    private suspend fun onRetrieveDataSuccess(result: ResultSet): ModelResult<Result> =
        coroutineScope {
            val modelResult = ModelResult<Result>()
            try {
                modelResult.entity = performOnSuccess(result)
                result.close()
            } catch (t: Throwable) {
                println("CreateCall error: ${t.printStackTrace()}")
                modelResult.exception = ConflictException()
            }
            modelResult
        }

    private suspend fun onRetrieveDataError() = coroutineScope {
        try {
            performOnError()
        } catch (t: Throwable) {
            println("CreateCall error: ${t.printStackTrace()}")
            null
        }
    }

    private fun retrieveData(query: String): ResultSet {
        println(query)
        return com.alan.manager.database.executeQuery(query)
    }

    @kotlin.jvm.Throws(Throwable::class)
    abstract suspend fun executeQuery(): String

    @kotlin.jvm.Throws(Throwable::class)
    abstract suspend fun performOnSuccess(result: ResultSet): Result

    @kotlin.jvm.Throws(Throwable::class)
    abstract suspend fun performOnError(): Result?

}