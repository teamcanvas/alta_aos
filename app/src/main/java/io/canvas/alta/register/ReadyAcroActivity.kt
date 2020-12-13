package io.canvas.alta.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.canvas.alta.R
import io.canvas.alta.databinding.ActivityReadyArcoBinding

@AndroidEntryPoint
class ReadyAcroActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var viewBinding: ActivityReadyArcoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_ready_arco)
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = viewModel

        viewModel.acroStep.observe(this, Observer { step ->
            if (step == RegisterStep.GO_SEARCH_DEVICE) {
                startActivity(Intent(this, SearchDeviceActivity::class.java))
                finish()
            }
        })

    }
}