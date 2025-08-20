package com.gxl.lingerieperfit.ui.base.network

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int,
        val errorBody: String?,
        val errorType: ErrorType = ErrorType.UNKNOWN
    ) : ApiResult<Nothing>()

    data object Loading : ApiResult<Nothing>()
}