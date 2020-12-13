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
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var bluetoothAdapter: BluetoothAdapter

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var adapter: DeviceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = viewModel

        viewModel.items.observe(this, Observer {
            Timber.d("${it?.size}")

            for (item in it) {
                Timber.d("name: ${item.name}, address: ${item.address}")
            }
        })

        viewModel.deviceClickEvent.observe(this, EventObserver {
            Toast.makeText(this, "deviceName: ${it.name}\naddress: ${it.address}", Toast.LENGTH_SHORT).show()
        })
    }
}