package com.kaspersky.kaspressample.device_tests

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.utils.SafeAssert.assertFalseSafely
import com.kaspersky.kaspressample.utils.SafeAssert.assertTrueSafely
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Rule
import org.junit.Test

class DeviceBluetoothSampleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun bluetoothSampleTest() {
        before {
            tryToggleBluetooth(shouldEnable = true)
        }.after {
            tryToggleBluetooth(shouldEnable = true)
        }.run {

            step("Disable bluetooth") {
                tryToggleBluetooth(shouldEnable = false)
                checkBluetooth(shouldBeEnabled = false)
            }

            step("Enable bluetooth") {
                tryToggleBluetooth(shouldEnable = true)
                checkBluetooth(shouldBeEnabled = true)
            }
        }
    }

    private fun tryToggleBluetooth(shouldEnable: Boolean) {
        if (shouldEnable) {
            device.bluetooth.enable()
        } else {
            device.bluetooth.disable()
        }
    }

    private fun BaseTestContext.checkBluetooth(shouldBeEnabled: Boolean) {
        try {
            if (shouldBeEnabled) assertTrueSafely { isBluetoothEnabled() } else assertFalseSafely { isBluetoothEnabled() }
        } catch (assertionError: AssertionError) {
            if (isBluetoothNotSupported()) return
            else throw assertionError
        }
    }

    private fun isBluetoothNotSupported(): Boolean =
        device.getBluetoothAdapter() == null

    private fun isBluetoothEnabled(): Boolean =
        device.getBluetoothAdapter()?.isEnabled ?: false

    private fun Device.getBluetoothAdapter(): BluetoothAdapter? =
        (this.targetContext.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager)?.adapter
}
