package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso_sample_core.device.DeviceSampleAccessibilityService
import com.kaspersky.kaspresso_sample_core.device.DeviceSampleActivity
import com.kaspersky.kaspressample.utils.SafeAssert.assertFalseSafely
import com.kaspersky.kaspressample.utils.SafeAssert.assertTrueSafely
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test

/**
 * Sometimes, this test is unstable.
 * The possible reason is a slow (not immediate) update of ContentResolver by which the test is checking
 */
class DeviceAccessibilitySampleTest : TestCase() {

    companion object {
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
        assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)

        before {
            device.accessibility.disable()
        }.after {
            device.accessibility.disable()
        }.run {

            step("Enable accessibility service") {
                device.accessibility.enable(
                    device.targetContext.packageName,
                    SERVICE_CLASS_NAME
                )
                assertTrueSafely { isAccessibilityServiceEnabled() }
            }

            step("Disable accessibility service") {
                device.accessibility.disable()
                assertFalseSafely { isAccessibilityServiceEnabled() }
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
