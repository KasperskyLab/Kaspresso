package com.kaspersky.kaspresso.tutorial

import android.content.res.Configuration
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        before {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.run {
            step("Open target screen") {
                MainScreen {
                    wifiActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            step("Check correct wifi status") {
                WifiScreen {
                    checkWifiButton.isVisible()
                    checkWifiButton.isClickable()
                    wifiStatus.hasEmptyText()
                    checkWifiButton.click()
                    wifiStatus.hasText(R.string.enabled_status)
                    device.network.toggleWiFi(false)
                    checkWifiButton.click()
                    wifiStatus.hasText(R.string.disabled_status)
                }
            }
            step("Rotate device and check wifi status") {
                WifiScreen {
                    device.exploit.rotate()
                    Assert.assertTrue(device.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    wifiStatus.hasText(R.string.disabled_status)
                }
            }
        }
    }
}
