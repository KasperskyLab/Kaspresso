package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.utils.SafeAssert.assertFalseSafely
import com.kaspersky.kaspressample.utils.SafeAssert.assertTrueSafely
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class DeviceNetworkSampleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    private val currentOsVersion = Build.VERSION.SDK_INT

    @Test
    fun networkSampleTest() {
        before {
            tryToggleNetwork(shouldEnable = true)
        }.after {
            tryToggleNetwork(shouldEnable = true)
        }.run {

            step("Disable network") {
                tryToggleNetwork(shouldEnable = false)
                assertFalseSafely { isDataConnected() }
                checkWifi(shouldBeEnabled = false)
            }

            step("Enable network") {
                tryToggleNetwork(shouldEnable = true)
                assertTrueSafely { isDataConnected() }
                checkWifi(shouldBeEnabled = true)
            }

            step("Toggle WiFi") {
                tryToggleWifi(shouldEnable = false)
                checkWifi(shouldBeEnabled = false)
                tryToggleWifi(shouldEnable = true)
                checkWifi(shouldBeEnabled = true)
            }

            step("Toggle Mobile data") {
                device.network.toggleMobileData(false)
                assertFalseSafely { isDataConnected() }
                device.network.toggleMobileData(true)
                assertTrueSafely { isDataConnected() }
            }
        }
    }

    private fun BaseTestContext.isDataConnected(): Boolean =
        if (currentOsVersion < Build.VERSION_CODES.N_MR1) isDataConnectedInLowAndroid() else isDataConnectedInHighAndroid()

    private fun BaseTestContext.isDataConnectedInHighAndroid(): Boolean {
        val manager = device.context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val cdl = CountDownLatch(1)
        var isConnected = false

        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        manager.requestNetwork(request, object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                manager.unregisterNetworkCallback(this)
                isConnected = false
                cdl.countDown()
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                manager.unregisterNetworkCallback(this)
                isConnected = true
                cdl.countDown()
            }
        })

        cdl.await(10L, TimeUnit.SECONDS)
        return isConnected
    }

    private fun BaseTestContext.isDataConnectedInLowAndroid(): Boolean {
        val telephonyManager: TelephonyManager? =
            device.context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
        if (telephonyManager?.simState != TelephonyManager.SIM_STATE_READY) {
            return false
        }
        return Settings.Global.getInt(device.context.contentResolver, "mobile_data", 0) == 1
    }

    private fun BaseTestContext.checkWifi(shouldBeEnabled: Boolean) {
        try {
            if (shouldBeEnabled) assertTrueSafely { isWiFiEnabled() } else assertFalseSafely { isWiFiEnabled() }
        } catch (assertionError: AssertionError) {
            // There is no mind to check wi-fi in Android emulators before Android 7.1 because
            // wi-fi doesn't have a simulated Wi-Fi access point on such emulators
            // that's why we just skip the wi-fi check on Android below 7.1
            if (currentOsVersion < Build.VERSION_CODES.N_MR1) return
            else throw assertionError
        }
    }

    private fun BaseTestContext.isWiFiEnabled(): Boolean =
        (device.context.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.isWifiEnabled
            ?: throw IllegalStateException("WifiManager is unavailable")

    private fun tryToggleNetwork(shouldEnable: Boolean) {
        try {
            if (shouldEnable) {
                device.network.enable()
            } else {
                device.network.disable()
            }
        } catch (ex: ActivityNotFoundException) { // There's no WIFI activity on AVD with API < 25
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) return
            throw ex
        }
    }

    private fun tryToggleWifi(shouldEnable: Boolean) {
        try {
            device.network.toggleWiFi(shouldEnable)
        } catch (ex: ActivityNotFoundException) { // There's no WIFI activity on AVD with API < 25
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) return
            throw ex
        }
    }
}
