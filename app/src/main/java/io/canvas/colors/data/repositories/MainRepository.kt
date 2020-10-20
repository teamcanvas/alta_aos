package io.canvas.colors.data.repositories

import androidx.lifecycle.LiveData
import io.canvas.colors.data.models.Resource
import io.canvas.colors.data.models.TestResponse
import io.canvas.colors.data.models.binData
import io.canvas.colors.data.remote.ApiResponse
import io.canvas.colors.data.remote.ApiSuccessResponse
import io.canvas.colors.data.remote.AppApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: AppApiService
) {

//    fun testRequest(): LiveData<Resource<binData>> {
//        return object : NetworkBoundResource<binData, TestResponse>() {
//            override fun createCall(): LiveData<ApiResponse<TestResponse>> {
//                return api.testRequest()
//            }
//
//            override fun processResponse(response: ApiSuccessResponse<TestResponse>): binData {
//                return response.body.headeres
//            }
//
//        }.asLiveData()
//    }
}

