package io.canvas.alta.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.canvas.alta.DeviceListAdapter
import io.canvas.alta.R
import io.canvas.alta.databinding.ActivitySearchDeviceBinding

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

    }
}