package com.vazch.mvvmtemplate.core

sealed class AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>()
    data class Error(
        val code: Int? = null,
        val message: String = "Unknown error",
        val cause: Throwable? = null
    ) : AppResult<Nothing>()
}