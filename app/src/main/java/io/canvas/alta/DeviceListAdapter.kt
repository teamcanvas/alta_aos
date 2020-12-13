package io.canvas.alta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.canvas.alta.data.models.BLEDevice
import io.canvas.alta.databinding.ItemDeviceBinding
import io.canvas.alta.register.SearchDeviceViewModel
import timber.log.Timber

class DeviceListAdapter(private val viewModel: SearchDeviceViewModel) :
    ListAdapter<BLEDevice, DeviceListAdapter.ViewHolder>(DeviceListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchDeviceViewModel: SearchDeviceViewModel, item: BLEDevice) {
            binding.viewModel = searchDeviceViewModel
            binding.item = item
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDeviceBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class DeviceListDiffCallback : DiffUtil.ItemCallback<BLEDevice>() {
    override fun areItemsTheSame(oldItem: BLEDevice, newItem: BLEDevice): Boolean {
        Timber.d("areitemsthesame: ${oldItem.address == newItem.address}")
        return oldItem.address == newItem.address
    }

    override fun areContentsTheSame(oldItem: BLEDevice, newItem: BLEDevice): Boolean {
        Timber.d("arecontentsthesame: ${oldItem.equals(newItem)}")
        return oldItem == newItem
    }
}

@BindingAdapter("app:deviceList")
fun setItems(listView: RecyclerView, items: List<BLEDevice>?) {
    Timber.d("items in binding adapter: ${items?.size}")
    items?.let {
        (listView.adapter as DeviceListAdapter).submitList(items.toMutableList())
    }
}