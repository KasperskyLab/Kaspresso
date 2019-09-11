package com.kaspersky.kaspressample.device_tests

import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceInternetSampleTest : TestCase() {

    companion object {
        private const val NETWORK_ESTABLISHMENT_DELAY = 1_500L
    }

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, true, true)

    @Test
    fun internetSampleTest() {
        before {
            device.network.enable()
        }.after {
            device.network.enable()
        }.run {

            step("Disable internet") {
                device.network.disable()
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertTrue(isDisconnected())
            }

            step("Enable internet") {
                device.network.enable()
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertFalse(isDisconnected())
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

    private fun BaseTestContext.isDisconnected(): Boolean =
        device.context.getSystemService(ConnectivityManager::class.java).activeNetworkInfo == null

    private fun BaseTestContext.isWiFiEnabled(): Boolean =
        device.context.getSystemService(WifiManager::class.java).isWifiEnabled
}