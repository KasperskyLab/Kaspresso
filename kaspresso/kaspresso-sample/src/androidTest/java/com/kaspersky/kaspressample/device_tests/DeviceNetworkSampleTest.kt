package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DeviceNetworkSampleTest : TestCase() {

    companion object {
        private const val NETWORK_ESTABLISHMENT_DELAY = 1_500L
    }

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
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertFalse(isDataConnected())
                assertFalse(isWiFiEnabled())
            }

            step("Enable network") {
                device.network.enable()
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertTrue(isDataConnected())
                assertTrue(isWiFiEnabled())
            }

            step("Toggle WiFi") {
                device.network.toggleWiFi(false)
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertFalse(isWiFiEnabled())

                device.network.toggleWiFi(true)
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertTrue(isWiFiEnabled())
            }
        }
    }

    private fun BaseTestContext.isDataConnected(): Boolean =
        device.context.getSystemService(ConnectivityManager::class.java).activeNetworkInfo != null

    private fun BaseTestContext.isWiFiEnabled(): Boolean =
        device.context.getSystemService(WifiManager::class.java).isWifiEnabled
}