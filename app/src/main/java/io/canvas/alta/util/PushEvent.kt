package io.canvas.alta.util

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PushEvent: LiveData<String>() {

    private val listener = { data: String ->
        postValue(data)

        /**
         * in main thread...
         */
        //value = price
    }


    override fun onActive() {
        super.onActive()
        requestUpdate(listener)
    }

    override fun onInactive() {
        super.onInactive()
    }

    fun requestUpdate(listener: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)
            listener("test22")
        }
    }

    fun updateValue(data: String) {
        value = data
    }

    companion object {
        private lateinit var sInstance: PushEvent

        @MainThread
        fun getInstance(): PushEvent {
            sInstance = if (::sInstance.isInitialized) sInstance else PushEvent()
            return sInstance
        }
    }

}