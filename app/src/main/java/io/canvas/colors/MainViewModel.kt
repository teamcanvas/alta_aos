package io.canvas.colors

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.canvas.colors.data.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
): ViewModel() {

    val data = repository.testRequest()

}