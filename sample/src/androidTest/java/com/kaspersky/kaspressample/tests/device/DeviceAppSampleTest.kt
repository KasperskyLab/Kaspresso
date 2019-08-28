package com.kaspersky.kaspressample.tests.device

import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceAppSampleTest : TestCase() {

    @Test
    fun test() {
        before {
        }.after {
        }.run {

            step("Install unavailable apk") {

                val apkName = "unreachable.apk"

                try {
                    device.apps.install(apkName)
                } catch (ex: AdbServerException) {
                    // APK file should be reachable from desktop server.
                    assertTrue(
                        "failed to stat $apkName: No such file or directory" in ex.message
                    )
                }
            }

            step("Delete settings application") {

                try {
                    AdbServer.performAdb("uninstall com.android.settings")
                } catch (ex: AdbServerException) {
                    // deleting system app caused error.
                    assertTrue("exitCode=1" in ex.message)
                }
            }
        }
    }
}