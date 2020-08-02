package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import androidx.test.rule.ActivityTestRule
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
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, true, true)

    @Test
    fun networkSampleTest() {
        before {
            device.network.enable()
        }.after {
            device.network.enable()
        }.run {

            step("Disable network") {
                device.network.disable()
                assertFalseSafely { isDataConnected() }
                assertFalseSafely { isWiFiEnabled() }
            }

            step("Enable network") {
                device.network.enable()
                assertTrueSafely { isDataConnected() }
                assertTrueSafely { isWiFiEnabled() }
            }

            step("Toggle WiFi") {
                device.network.toggleWiFi(false)
                assertFalseSafely { isWiFiEnabled() }
                device.network.toggleWiFi(true)
                assertTrueSafely { isWiFiEnabled() }
            }

            step("Toggle Mobile data") {
                device.network.toggleMobileData(false)
                assertFalseSafely { isDataConnected() }
                device.network.toggleMobileData(true)
                assertTrueSafely { isDataConnected() }
            }
        }
    }

    private fun BaseTestContext.isDataConnected(): Boolean {
        val manager = device.context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val cdl = CountDownLatch(1)
        var isConnected: Boolean = false

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
        return isConnected;
    }

    private fun BaseTestContext.isWiFiEnabled(): Boolean =
        (device.context.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.isWifiEnabled ?: throw IllegalStateException("WifiManager is unavailable")
}