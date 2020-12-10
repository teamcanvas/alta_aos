package io.canvas.alta.di

import android.bluetooth.BluetoothAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class BluetoothModule {

    @Provides
    fun provideBluetoothAdapter(): BluetoothAdapter {
        return BluetoothAdapter.getDefaultAdapter()
    }
}