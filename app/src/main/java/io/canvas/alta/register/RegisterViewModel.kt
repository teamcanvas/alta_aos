package io.canvas.alta.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel @ViewModelInject constructor() : ViewModel() {
    val acroStep = MutableLiveData<RegisterStep>().apply {
        value = RegisterStep.READY_ARCO_1
    }

    fun acroNextStep() {
        when (acroStep.value) {
            RegisterStep.READY_ARCO_1 -> {
                acroStep.value = RegisterStep.READY_ACRO_2
            }
            RegisterStep.READY_ACRO_2 -> {
                acroStep.value = RegisterStep.READY_ACRO_3
            }
            else -> {
                //test
            }
        }
    }
}

enum class RegisterStep(val step: String) {
    READY_ARCO_1("Take Acro out of the package and get ready."),
    READY_ACRO_2("Connect the cable to the port on the back of Acro."),
    READY_ACRO_3("Your Acro has just been preheated!");
}