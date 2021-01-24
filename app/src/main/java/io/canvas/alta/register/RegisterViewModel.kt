package io.canvas.alta.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel @ViewModelInject constructor() : ViewModel() {
    val acroStep = MutableLiveData<RegisterStep>().apply {
        value = RegisterStep.READY_ACRO_1
    }

    fun acroNextStep() {
        when (acroStep.value) {
            RegisterStep.READY_ACRO_1 -> {
                acroStep.value = RegisterStep.READY_ACRO_2
            }
            RegisterStep.READY_ACRO_2 -> {
                acroStep.value = RegisterStep.READY_ACRO_3
            }
            RegisterStep.READY_ACRO_3 -> {
                acroStep.value = RegisterStep.GO_SEARCH_DEVICE
            }
        }
    }
}

enum class RegisterStep() {
    READY_ACRO_1,
    READY_ACRO_2,
    READY_ACRO_3,
    GO_SEARCH_DEVICE;
}