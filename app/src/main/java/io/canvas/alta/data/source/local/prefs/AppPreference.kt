package io.canvas.alta.data.source.local.prefs

import android.content.SharedPreferences
import io.canvas.alta.di.AppModule
import javax.inject.Inject

class AppPreference @Inject constructor(
    @AppModule.AppSharedPreference private val appPreference: SharedPreferences,
    @AppModule.ConfigSharedPreference private val configPreference: SharedPreferences
) {

    fun getBoolValue(): Boolean {
        return appPreference.getBoolean("key_boolean", false)
    }

    fun getConfigValue(): Boolean {
        return configPreference.getBoolean("config_boolean", true)
    }
}