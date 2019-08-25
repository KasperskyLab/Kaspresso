package com.kaspersky.kaspressample.tests.device

import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.devicesample.DeviceSampleActivity
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
        const val NETWORK_ESTABLISHMENT_DELAY = 1_500L
    }

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, true, true)

    @Test
    fun internetSampleTest() {
        before {
            device.internet.enable()
        }.after {
            device.internet.enable()
        }.run {

            step("Disable internet") {
                device.internet.disable()
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertFalse(isConnectedOrConnecting())
            }

            step("Enable internet") {
                device.internet.enable()
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertTrue(isConnectedOrConnecting())
            }

            step("Toggle WiFi") {
                device.internet.toggleWiFi(false)
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertFalse(isWiFiEnabled())

                device.internet.toggleWiFi(true)
                Screen.idle(NETWORK_ESTABLISHMENT_DELAY)
                assertTrue(isWiFiEnabled())
            }
        }
    }

    private fun BaseTestContext.isConnectedOrConnecting(): Boolean {
        val manager = device.context.getSystemService(ConnectivityManager::class.java)
        return manager.activeNetworkInfo != null
    }

    private fun BaseTestContext.isWiFiEnabled(): Boolean =
        device.context.getSystemService(WifiManager::class.java).isWifiEnabled
}