package io.canvas.alta.data.remote

import androidx.lifecycle.LiveData
import io.canvas.alta.data.models.TestResponse
import retrofit2.http.GET

interface AppApiService {

    @GET("/get")
    fun testRequest(): LiveData<ApiResponse<TestResponse>>
}