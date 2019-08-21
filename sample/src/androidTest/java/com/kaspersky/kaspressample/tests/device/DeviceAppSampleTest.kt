package com.kaspersky.kaspressample.tests.device

import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspresso.device.server.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
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

                val apkName = "your_apk_on_host.apk"

                try {
                    device.apps.install(apkName)
                } catch (ex: AdbServerException) {
                    // APK file should be reachable from desktop server.
                    Assert.assertTrue(
                        "failed to stat $apkName: No such file or directory" in ex.message
                    )
                }
            }
        }
    }
}