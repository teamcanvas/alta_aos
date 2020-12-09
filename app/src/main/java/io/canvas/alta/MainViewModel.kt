package io.canvas.alta

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Handler
import androidx.core.os.postDelayed
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.canvas.alta.data.models.BLEDevice
import io.canvas.alta.utils.ListLiveData
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
        private val bluetoothAdapter: BluetoothAdapter,
) : ViewModel() {

    private val _isScanning = MutableLiveData<Boolean>().apply { value = false }
    val isScanning: LiveData<Boolean> = _isScanning

    private val _deviceClickEvent = MutableLiveData<Event<BLEDevice>>()
    val deviceClickEvent: LiveData<Event<BLEDevice>> = _deviceClickEvent

    val items = ListLiveData<BLEDevice>()

    fun startScan() {
        val leScanner = bluetoothAdapter.bluetoothLeScanner
        items.clear(true)

        Handler().postDelayed(delayInMillis = 6000L) {
            _isScanning.value = false
            leScanner?.stopScan(mScanCallback)
            Timber.d("BLE Scan Sttoped")
        }
        _isScanning.value = true
        leScanner?.startScan(mScanCallback)
        Timber.d("BLE Scan Started")
    }

    private val mScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            processResult(result)
        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            for (result in results) {
                processResult(result)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Timber.e("ScanERROR: $errorCode")
        }

        private fun processResult(result: ScanResult) {
            //TODO 컬러 디바이스 이름에 맞춰 contains 로 변경
            if (result.device.name != null)
                if (!hasDuplicates(items.value!!.toArray())) {
                    items.add(BLEDevice(result.device.name ?: "N/A", result.device.address))
                }
        }
    }

    private fun <T> hasDuplicates(arr: Array<T>): Boolean {
        return arr.size != arr.distinct().count()
    }

    fun clickDevice(device: BLEDevice) {
        _deviceClickEvent.value = Event(device)
    }
}