package com.kaspersky.kaspressample.tests.device

import android.provider.Settings
import android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.devicesample.DeviceSampleAccessibilityService
import com.kaspersky.kaspressample.devicesample.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceAccessibilitySampleTest : TestCase() {

    @get:Rule
    val activityRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    companion object {
        private const val SETTINGS_UPDATE_DELAY = 1_000L
        private val SERVICE_CLASS_NAME = DeviceSampleAccessibilityService::class.java.canonicalName!!
    }

    @Test
    fun accessibilitySampleTest() {
        before {
            device.accessibility.disable()
        }.after {
            device.accessibility.disable()
        }.run {
            step("Enable accessibility service") {
                device.accessibility.enable(device.targetContext.packageName, SERVICE_CLASS_NAME)
                Screen.idle(SETTINGS_UPDATE_DELAY)

                assertTrue(isAccessibilityServiceEnabled())
            }

            step("Disable accessibility service") {
                device.accessibility.disable()
                Screen.idle(SETTINGS_UPDATE_DELAY)

                assertFalse(isAccessibilityServiceEnabled())
            }
        }
    }

    private fun BaseTestContext.isAccessibilityServiceEnabled(): Boolean {
        return Settings.Secure.getString(device.context.contentResolver,
            ENABLED_ACCESSIBILITY_SERVICES)?.contains(SERVICE_CLASS_NAME) ?: false
    }
}