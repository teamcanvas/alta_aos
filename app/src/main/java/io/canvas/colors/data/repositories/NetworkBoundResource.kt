package io.canvas.colors.data.repositories

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.canvas.colors.data.models.Resource
import io.canvas.colors.data.remote.ApiEmptyResponse
import io.canvas.colors.data.remote.ApiErrorResponse
import io.canvas.colors.data.remote.ApiResponse
import io.canvas.colors.data.remote.ApiSuccessResponse

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(){

    private val result = MediatorLiveData<Resource<ResultType>>().apply {
        value = Resource.loading(null)
    }

    init {
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>){
        if(result.value != newValue)
            result.value = newValue
    }

    private fun fetchFromNetwork(){
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            when(response) {
                is ApiSuccessResponse -> {
                    setValue(Resource.success(processResponse(response)))
                }
                is ApiEmptyResponse -> {
                    setValue(Resource.success(null))
                }
                is ApiErrorResponse -> {
                    onFetchFailed(response)
                    val errorMessage = when(response.errorCode){
                        -1 ->  "서버 연결에 실패했습니다."
                        401 -> "토큰이 만료되었습니다.\n다시 로그인해주세요."
                        else -> response.errorMessage
                    }
                    setValue(Resource.error(errorMessage, null, code=response.errorCode))
                }
            }
        }
    }

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun processResponse(response: ApiSuccessResponse<RequestType>): ResultType

    protected open fun onFetchFailed(response: ApiErrorResponse<RequestType>) {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>
}