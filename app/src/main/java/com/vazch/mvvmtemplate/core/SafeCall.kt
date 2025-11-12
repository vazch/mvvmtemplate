package com.vazch.mvvmtemplate.core

import retrofit2.Response

suspend inline fun <T> safeApiCall(crossinline call: suspend () -> Response<T>): AppResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) AppResult.Success(body)
            else AppResult.Error(code = response.code(), message = "Empty body")
        } else {
            AppResult.Error(code = response.code(), message = response.message())
        }
    } catch (t: Throwable) {
        AppResult.Error(message = t.message ?: "Network error", cause = t)
    }
}