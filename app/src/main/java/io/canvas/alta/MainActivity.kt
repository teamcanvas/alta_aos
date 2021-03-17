package io.canvas.alta

import android.bluetooth.BluetoothAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.canvas.alta.databinding.ActivityMainBinding
import io.canvas.alta.ui.acro.AcroFragment
import io.canvas.alta.ui.home.HomeFragment
import io.canvas.alta.ui.settings.SettingsFragment
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var bluetoothAdapter: BluetoothAdapter

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = viewModel

        supportFragmentManager.beginTransaction()
            .replace(viewBinding.container.id, HomeFragment())
            .commit()

        viewBinding.bottomBar.onItemSelected = { position ->
            Timber.d("bottombar: $position")
            when (position) {
                0 -> setFragment(BottomBarItem.HOME)
                1 -> setFragment(BottomBarItem.ACRO)
                2 -> setFragment(BottomBarItem.SETTINGS)
            }
        }
    }

    private fun setFragment(item: BottomBarItem) {
        when (item) {
            BottomBarItem.HOME -> {
                supportFragmentManager.beginTransaction()
                    .replace(viewBinding.container.id, HomeFragment())
                    .commit()
            }

            BottomBarItem.ACRO -> {
                supportFragmentManager.beginTransaction()
                    .replace(viewBinding.container.id, AcroFragment())
                    .commit()
            }

            BottomBarItem.SETTINGS -> {
                supportFragmentManager.beginTransaction()
                    .replace(viewBinding.container.id, SettingsFragment())
                    .commit()
            }
        }
    }
}

enum class BottomBarItem() {
    HOME,
    ACRO,
    SETTINGS;
}