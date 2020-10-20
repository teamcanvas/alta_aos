package io.canvas.colors.data.models

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("headers") val headeres: binData
)

data class binData(
    @SerializedName("Accept-Encoding") val accept: String,
    @SerializedName("User-Agent") val contentType: String
)