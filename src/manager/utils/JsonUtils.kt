package com.alan.manager.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.util.function.Consumer
import java.util.stream.Collectors
import java.util.stream.IntStream

object JsonUtils {

    fun getJsonArrayFromResultSet(resultSet: ResultSet): JSONArray {
        val md: ResultSetMetaData = resultSet.metaData
        val numCols = md.columnCount

        val jsonArray = JSONArray()

        val colNames = IntStream.range(0, numCols)
            .mapToObj { i: Int ->
                try {
                    return@mapToObj md.getColumnName(i + 1)
                } catch (e: SQLException) {
                    e.printStackTrace()
                    return@mapToObj "?"
                }
            }
            .collect(Collectors.toList())
        while (resultSet.next()) {
            val row = JSONObject()
            colNames.forEach(Consumer { cn: String? ->
                try {
                    row.put(cn,resultSet.getObject(cn))
                } catch (e: JSONException) {
                    e.printStackTrace()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            })
            jsonArray.put(row)
        }
        resultSet.close()
        return jsonArray
    }

    @Throws(JsonSyntaxException::class)
    fun <T> convertJsonObjectToClass(json: JSONObject, classOfT: Class<T>?): T {
        val jsonString = json.toString()
        return Gson().fromJson(jsonString, classOfT)
    }

    @Throws(JsonSyntaxException::class)
    fun <T> convertJsonArrayToClass(jsonArray: JSONArray, classOfT: Class<T>?): List<T> {
        val elementsList = mutableListOf<T>()
        jsonArray.forEach { anyJson ->
            val jsonObject = anyJson as JSONObject
            val element = convertJsonObjectToClass(jsonObject, classOfT)
            elementsList.add(element)
        }
        return elementsList
    }

}