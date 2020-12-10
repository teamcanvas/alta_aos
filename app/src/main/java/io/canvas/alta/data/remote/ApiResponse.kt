package io.canvas.alta.data.remote

import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

internal const val UNKNOWN_CODE = -1

@Suppress("unused")
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse(response.code())
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val jsonObject = JSONObject(response.errorBody()?.string()?:response.message())
                val result: String? = jsonObject.getString("detail")

                ApiErrorResponse(response.code(), result?: "Unknown Error")
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            Timber.e(error.message)
            return ApiErrorResponse(errorCode, error.message ?: "Unknown Error!")
        }
    }
}


class ApiEmptyResponse<T>(val code: Int) : ApiResponse<T>()
data class ApiErrorResponse<T>(val errorCode: Int, val errorMessage: String): ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()