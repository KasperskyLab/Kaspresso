package com.kaspersky.kaspresso.tutorial.test

import android.content.res.Configuration
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.WiFiScreen
import com.kaspersky.kaspresso.tutorial.wifi.WiFiActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WiFiTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<WiFiActivity>()

    @Test
    fun check_wifi_status() = before {
        device.network.disable()
        device.network.toggleWiFi(false)
    }.after {
        device.network.enable()
        device.network.toggleWiFi(true)
    }.run {

        step("Check correct wifi status") {
            WiFiScreen {
                wifiStatusButton.click()
                wifiStatusTitle.containsText("disabled")
            }
        }
    }

    @Test
    fun check_same_wifi_status_after_rotation() = before {
        device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        device.exploit.setAutoRotationEnabled(true)
        device.network.disable()
        device.network.toggleWiFi(false)
    }.after {
        device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        device.exploit.setAutoRotationEnabled(true)
        device.network.enable()
        device.network.toggleWiFi(true)
    }.run {

        step("Check correct wifi status") {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            WiFiScreen {
                wifiStatusButton.click()
                wifiStatusTitle.containsText("disabled")
            }
        }

        step("Rotate device and check wifi status") {
            device.exploit.rotate()
            Assert.assertTrue(Configuration.ORIENTATION_LANDSCAPE == device.context.resources.configuration.orientation)
            WiFiScreen.wifiStatusTitle.containsText("disabled")
        }
    }
}
