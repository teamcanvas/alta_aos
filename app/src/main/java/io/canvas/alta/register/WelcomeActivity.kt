package io.canvas.alta.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.canvas.alta.R
import io.canvas.alta.databinding.ActivityWelcomeBinding

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var viewBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = viewModel

    }
}