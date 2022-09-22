package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.screen.DeviceSampleScreen
import com.kaspersky.kaspressample.utils.SafeAssert.assertTrueSafely
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test

class DevicePermissionsSampleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun permissionsSampleTest() {
        // Run only on devices with Android M or later and skip the test otherwise.
        assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        before {
            adbServer.performShell("pm revoke ${device.targetContext.packageName} ${Manifest.permission.READ_CALL_LOG}")
        }.after {
        }.run {

            step("Request permissions") {
                DeviceSampleScreen {
                    // Button click requests permission using default Android dialog
                    requestPermissionButton {
                        click()
                    }
                }

                device.permissions.apply {
                    assertTrueSafely { isDialogVisible() }
                    allowViaDialog()
                }

                // Contacts permission should be granted now
                assertTrueSafely { hasCallLogPermission() }
            }
        }
    }

    private fun BaseTestContext.hasCallLogPermission(): Boolean =
        device.targetContext.checkSelfPermission(Manifest.permission.READ_CALL_LOG) ==
                PackageManager.PERMISSION_GRANTED
}
