package com.genxlead.lingeriePer_FitApp.ui.base.network

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                handleException(throwable)
            }
        }
    }
}


private fun handleException(throwable: Throwable): ApiResult.Error {
    println("throwable::$throwable")
    return when (throwable) {
        is ServerResponseException, is ClientRequestException -> {
            println("throwable::${throwable.response.status.value}")
            val errorCode = throwable.response.status.value
            val errorType = getErrorTypeFromCode(throwable.hashCode())
            val errorMessage = getErrorMessage(errorType, throwable.message)

            ApiResult.Error(
                isNetworkError = false,
                errorCode = throwable.response.status.value,
                errorBody = errorMessage,
                errorType = errorType
            )
        }

        is SocketTimeoutException -> {
            ApiResult.Error(
                isNetworkError = true,
                errorCode = 408,
                errorBody = "Request timed out. Please try again.",
                errorType = ErrorType.TIMEOUT
            )
        }

        is IllegalStateException -> {
            ApiResult.Error(
                isNetworkError = false,
                errorCode = 500,
                errorBody = throwable.message ?: "Internal application error occurred.",
                errorType = ErrorType.INTERNAL_SERVER
            )
        }

        is NoTransformationFoundException -> {
            ApiResult.Error(
                isNetworkError = false,
                errorCode = 406,
                errorBody = "No transformation found for the response",
                errorType = ErrorType.INTERNAL_SERVER
            )
        }

        else -> {
            ApiResult.Error(
                isNetworkError = false,
                errorCode = 502,
                errorBody = throwable.message ?: "Unknown error occurred.",
                errorType = ErrorType.UNKNOWN
            )
        }
    }
}

// Function to determine the error type based on HTTP status code
private fun getErrorTypeFromCode(code: Int): ErrorType {
    return when (code) {
        in 400..499 -> {
            when (code) {
                400 -> ErrorType.BAD_REQUEST
                401 -> ErrorType.UNAUTHORIZED
                403 -> ErrorType.FORBIDDEN
                404 -> ErrorType.NOT_FOUND
                409 -> ErrorType.CONFLICT
                422 -> ErrorType.UNPROCESSABLE_ENTITY
                429 -> ErrorType.TOO_MANY_REQUESTS
                else -> ErrorType.CLIENT_ERROR
            }
        }

        in 500..599 -> {
            when (code) {
                500 -> ErrorType.INTERNAL_SERVER
                502 -> ErrorType.BAD_GATEWAY
                503 -> ErrorType.SERVICE_UNAVAILABLE
                504 -> ErrorType.GATEWAY_TIMEOUT
                else -> ErrorType.SERVER_ERROR
            }
        }

        else -> ErrorType.UNKNOWN
    }
}

// Custom function to provide user-friendly error messages based on the error type
private fun getErrorMessage(errorType: ErrorType, errorBody: String?): String {
    return when (errorType) {
        ErrorType.BAD_REQUEST -> errorBody ?: "Invalid request. Please check your data."
        ErrorType.UNAUTHORIZED -> "Authentication failed. Please login again."
        ErrorType.FORBIDDEN -> "You don't have permission to access this ApiResult."
        ErrorType.NOT_FOUND -> "Requested resource not found."
        ErrorType.CONFLICT -> "Conflict occurred with the current state of the resource."
        ErrorType.UNPROCESSABLE_ENTITY -> errorBody
            ?: "The request was well-formed but contains invalid data."

        ErrorType.TOO_MANY_REQUESTS -> "Too many requests. Please try again later."
        ErrorType.INTERNAL_SERVER -> "Server encountered an error. Please try again later."
        ErrorType.BAD_GATEWAY -> "Server is temporarily unavailable. Please try again later."
        ErrorType.SERVICE_UNAVAILABLE -> "Service is currently unavailable. Please try again later."
        ErrorType.GATEWAY_TIMEOUT -> "Server took too long to respond. Please try again."
        ErrorType.CLIENT_ERROR -> errorBody ?: "Client error occurred."
        ErrorType.SERVER_ERROR -> errorBody ?: "Server error occurred."
        ErrorType.NETWORK_UNAVAILABLE -> "Network unavailable. Please check your connection."
        ErrorType.TIMEOUT -> "Request timed out. Please try again."
        ErrorType.EMPTY_RESPONSE -> "Server returned an empty response."
        ErrorType.GRAPHQL -> errorBody ?: "GraphQL error occurred."
        ErrorType.UNKNOWN -> errorBody ?: "Unknown error occurred."
    }
}


enum class ErrorType {
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    CONFLICT,
    UNPROCESSABLE_ENTITY,
    TOO_MANY_REQUESTS,
    INTERNAL_SERVER,
    BAD_GATEWAY,
    SERVICE_UNAVAILABLE,
    GATEWAY_TIMEOUT,
    CLIENT_ERROR,
    SERVER_ERROR,
    NETWORK_UNAVAILABLE,
    TIMEOUT,
    EMPTY_RESPONSE,
    GRAPHQL, // Kept for backwards compatibility
    UNKNOWN
}
