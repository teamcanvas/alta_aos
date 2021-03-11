package io.canvas.alta

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.canvas.alta.databinding.ActivityWelcomeBinding
import io.canvas.alta.regist.SearchDeviceActivity

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        viewBinding.lifecycleOwner = this

        viewBinding.nextButton.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SearchDeviceActivity::class.java))
        }

    }
}