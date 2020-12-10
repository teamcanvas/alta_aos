package io.canvas.alta.di

import android.os.Build
import com.orhanobut.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.canvas.alta.BuildConfig
import io.canvas.alta.data.remote.AppApiService
import io.canvas.alta.utils.LiveDataCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

class UserAgentInterceptor(private val userAgent: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val newRequest = originRequest.newBuilder().header("User-Agent", userAgent).build()
        return chain.proceed(newRequest)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
open class NetworkModule {

    companion object {
        const val OKHTTP_TAG: String = "OKHTTP"
        const val BASE_URL: String = "https://google.com"
    }

    @Provides
    @Singleton
    open fun provideOkHttp3Client(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            try {
                JSONObject(message)
                Logger.t(OKHTTP_TAG).json(message)
            } catch (error: JSONException) {
                if (message.contains("http"))
                    Logger.t(OKHTTP_TAG).d(message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor(UserAgentInterceptor("Android ${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE}) ${Build.MODEL} SDK${Build.VERSION.SDK_INT}"))

        if (BuildConfig.DEBUG)
            builder.addInterceptor(interceptor)

        return builder.build()
    }

    @Provides
    @Named("appApi")
    @Singleton
    open fun provideRetrofitService(client: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

        builder.client(client)

        return builder.build()
    }

    @Provides
    @Singleton
    open fun provideAppApiService(@Named("appApi") retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)

}