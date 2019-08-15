package com.kaspersky.kaspressample.tests.device

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.viewactions.orientation.Orientation
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceApiSample : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            device.exploit.setOrientation(Orientation.Landscape)
            activityTestRule.launchActivity(null)
        }.after {
            device.exploit.setOrientation(Orientation.Portrait)
        }.run {

            step("Simple ADB usage - success") {
                val adbResult = AdbServer.performAdb("get-state")
                assertTrue("device" in adbResult.first())
            }

            step("Simple ADB usage - error") {
                try {
                    AdbServer.performAdb("testme")
                } catch (ex: AdbServerException) {
                    assertTrue("unknown command testme" in ex.message)
                }
            }

            step("Device.apps API sample") {
                try {
                    device.apps.install("your_apk_on_host.apk")
                } catch (ex: AdbServerException) {
                    // APK file should be reachable from desktop server.
                    assertTrue(
                        "failed to stat your_apk_on_host.apk: No such file or directory" in ex.message
                    )
                }

                try {
                    device.apps.uninstall("com.android.settings")
                } catch (ex: AdbServerException) {
                    // deleting system app caused error.
                    assertTrue(
                        "Failure [DELETE_FAILED_INTERNAL_ERROR]" in ex.message
                    )
                }
            }
        }
    }
}
