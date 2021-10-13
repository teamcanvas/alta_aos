package io.canvas.alta

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import io.canvas.alta.util.CustomDebugTree
import timber.log.Timber

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.e("onCreate")

        Logger.addLogAdapter(AndroidLogAdapter())
        if (BuildConfig.DEBUG) Timber.plant(CustomDebugTree())
    }
}