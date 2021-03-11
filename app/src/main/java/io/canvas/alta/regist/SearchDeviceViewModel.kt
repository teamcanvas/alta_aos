package io.canvas.alta.regist

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Handler
import androidx.core.os.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.canvas.alta.Event
import io.canvas.alta.data.models.BLEDevice
import io.canvas.alta.utils.ListLiveData
import timber.log.Timber
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * Created by Junseo on 2020-12-13.
 */

@HiltViewModel
class SearchDeviceViewModel @Inject constructor(
    private val bluetoothAdapter: BluetoothAdapter
) : ViewModel() {

    private val _availableDevicesCount = MutableLiveData<Int>().apply { value = 0 }
    val availableDevicesCount: LiveData<Int> = _availableDevicesCount

    private val _isScanning = MutableLiveData<Boolean>().apply { value = false }
    val isScanning: LiveData<Boolean> = _isScanning

    private val _deviceClickEvent = MutableLiveData<Event<BLEDevice>>()
    val deviceClickEvent: LiveData<Event<BLEDevice>> = _deviceClickEvent

    private val tempDeviceList: ArrayList<BLEDevice> = ArrayList()
    val deviceList = ListLiveData<BLEDevice>()

    private val _nextProcessEvent = MutableLiveData<Event<NextProcess>>()
    val nextProcessEvent: LiveData<Event<NextProcess>> = _nextProcessEvent

    fun startScan() {
        //TODO isScanning 사용할 것.

        val leScanner = bluetoothAdapter.bluetoothLeScanner
//        items.clear(true)
//        _availableDevicesCount.value = 0
//        tempDeviceList.clear()

//        val filters: MutableList<ScanFilter> = ArrayList()
//        val filter = ScanFilter.Builder()
//                .setDeviceName("Hue 조명 1")
//                .build()
//
//        filters.add(filter)
//
//        val settings = ScanSettings.Builder()
//                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
//                .setReportDelay(3000L)
//                .build()

        Handler().postDelayed(delayInMillis = 5000L) {
            _isScanning.value = false
            leScanner.stopScan(mScanCallback)
            Timber.d("BLE Scan Stopped")
            if (tempDeviceList.size > 0)
                _nextProcessEvent.value = Event(NextProcess.READY_TO_CONNECT)
            else
                _nextProcessEvent.value = Event(NextProcess.NO_DEVICE)
            //TODO 갯수따라 분기처리 하기
        }
        _isScanning.value = true

        //leScanner.startScan(filters, settings, mScanCallback)
        leScanner.startScan(mScanCallback)

        Timber.d("BLE Scan Started")
    }

    private val mScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            processResult(result)
            Timber.e("on scan result: ${result.device.address}, ${result.rssi}")
        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            Timber.e("on batch scan results: ${results.size}")
            for (result in results) {
                processResult(result)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Timber.e("ScanERROR: $errorCode")
        }
    }

    private fun processResult(result: ScanResult) {
        //TODO 컬러 디바이스 이름에 맞춰 contains 로 변경
        if (result.device.name != null)
            addItem(BLEDevice(result.device.name ?: "null", result.device.address))

    }

    private fun addItem(data: BLEDevice) {
        if (!tempDeviceList.contains(data)) {
            tempDeviceList.add(data)
            deviceList.add(data)
            _availableDevicesCount.value = (availableDevicesCount.value?.plus(1))
        }
    }

    fun clickDevice(device: BLEDevice) {
        _deviceClickEvent.value = Event(device)
    }
}

enum class NextProcess {
    READY_TO_CONNECT,
    SEARCHED_MORE_DEVICE,
    NO_DEVICE
}