package io.canvas.alta.regist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.canvas.alta.DeviceListAdapter
import io.canvas.alta.MainActivity
import io.canvas.alta.R
import io.canvas.alta.databinding.ActivitySearchDeviceBinding
import timber.log.Timber

/**
 * Created by Junseo on 2020-12-13.
 */
@AndroidEntryPoint
class SearchDeviceActivity : AppCompatActivity() {

    private val viewModel: SearchDeviceViewModel by viewModels()
    private lateinit var viewBinding: ActivitySearchDeviceBinding

    private lateinit var adapter: DeviceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_device)
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = viewModel

        adapter = DeviceListAdapter(viewModel)
        viewBinding.deviceListRecyclerView.adapter = adapter

        viewModel.startScan()

        viewModel.deviceList.observe(this, Observer {
            //if (it.size != 0)
            //Toast.makeText(this, "item was added", Toast.LENGTH_SHORT).show()
        })

        viewModel.nextProcessEvent.observe(this, {
            Timber.d(it.toString())
            startActivity(Intent(this@SearchDeviceActivity, MainActivity::class.java))
        })
    }
}