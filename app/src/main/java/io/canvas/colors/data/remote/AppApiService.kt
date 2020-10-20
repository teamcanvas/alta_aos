package io.canvas.colors.data.remote

import androidx.lifecycle.LiveData
import io.canvas.colors.data.models.TestResponse
import io.canvas.colors.data.models.binData
import retrofit2.http.GET

interface AppApiService {

    @GET("/get")
    fun testRequest(): LiveData<ApiResponse<TestResponse>>
}