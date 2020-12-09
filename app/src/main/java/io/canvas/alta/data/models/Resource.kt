package io.canvas.alta.data.models

import io.canvas.alta.data.models.Status.SUCCESS
import io.canvas.alta.data.models.Status.ERROR
import io.canvas.alta.data.models.Status.LOADING

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    var code: Int? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T>{
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?, code: Int?=null): Resource<T>{
            return Resource(ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T>{
            return Resource(LOADING, data, null)
        }

    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}