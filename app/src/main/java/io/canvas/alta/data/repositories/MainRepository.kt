package io.canvas.alta.data.repositories

import io.canvas.alta.data.remote.AppApiService
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

