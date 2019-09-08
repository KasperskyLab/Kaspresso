package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspressample.devicesample.DeviceSampleActivity
import com.kaspersky.kaspressample.screen.DeviceSampleScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DevicePermissionsSampleTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun permissionsSampleTest() {
        before {
            // Run only on devices with Android M or later and skip the test otherwise.
            assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        }.after {
        }.run {

            step("Request permissions") {
                DeviceSampleScreen {
                    // Button click requests permission using default Android dialog
                    requestPermissionButton {
                        click()
                    }
                }

                device.permissions.allowViaDialog()

                // Contacts permission should be granted now
                assertTrue(hasCallLogPermission())
            }
        }
    }

    private fun BaseTestContext.hasCallLogPermission(): Boolean =
        device.targetContext.checkSelfPermission(Manifest.permission.READ_CALL_LOG) ==
                PackageManager.PERMISSION_GRANTED
}
