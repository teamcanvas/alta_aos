package io.canvas.colors

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        Timber.plant(Timber.DebugTree())
        Timber.d("-------------------- [[ onCreate APP ]] --------------------")
        //TODO
        Timber.e("CHECK LOGGED IN SESSION!")
    }
}