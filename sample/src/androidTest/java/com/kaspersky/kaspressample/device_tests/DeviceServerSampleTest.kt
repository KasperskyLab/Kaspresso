package com.kaspersky.kaspressample.device_tests

import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceServerSampleTest : TestCase() {

    @Test
    fun serverSampleTest() {
        before {
        }.after {
        }.run {

            step("Execute command on host") {
                val result = AdbServer.performCmd("hostname")
                assertTrue(result.isNotEmpty())
            }

            step("Execute ADB command") {
                val command = "undefined_command"

                try {
                    AdbServer.performAdb(command)
                } catch (ex: AdbServerException) {
                    assertTrue("unknown command $command" in ex.message)
                }
            }

            step("Execute ADB Shell command") {
                val command = "pm list packages"

                val result = AdbServer.performShell(command)
                assertTrue("package:${device.targetContext.packageName}" in result.first())
            }
        }
    }
}