package io.canvas.alta.data.models

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("headers") val headers: binData
)

data class binData(
    @SerializedName("Accept-Encoding") val accept: String,
    @SerializedName("User-Agent") val contentType: String
)