package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.device.DeviceSampleAccessibilityService
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceAccessibilitySampleTest : TestCase() {

    companion object {
        private const val SETTINGS_UPDATE_DELAY = 1_000L
        private val SERVICE_CLASS_NAME = DeviceSampleAccessibilityService::class.java.canonicalName!!
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun accessibilitySampleTest() {
        before {
            assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            device.accessibility.disable()
        }.after {
            device.accessibility.disable()
        }.run {

            step("Enable accessibility service") {
                device.accessibility.enable(
                    device.targetContext.packageName,
                    SERVICE_CLASS_NAME
                )
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
        return Settings.Secure.getString(
            device.targetContext.contentResolver,
            ENABLED_ACCESSIBILITY_SERVICES
        )?.contains(SERVICE_CLASS_NAME) ?: false
    }
}