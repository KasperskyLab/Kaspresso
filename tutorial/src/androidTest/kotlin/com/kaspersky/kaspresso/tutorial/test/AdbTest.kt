package com.kaspersky.kaspresso.tutorial.test

import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Test

class AdbTest : TestCase() {

    @Test
    fun serverSampleTest() {
        run {

            step("Execute command on host") {
                val result = adbServer.performCmd("hostname")
                Assert.assertTrue(result.isNotEmpty())
            }

            step("Execute ADB command") {
                val command = "undefined_command"

                try {
                    adbServer.performAdb(command)
                } catch (ex: AdbServerException) {
                    Assert.assertTrue("unknown command $command" in ex.message)
                }
            }

            step("Execute ADB Shell command") {
                val command = "pm list packages"

                val result = adbServer.performShell(command)
                Assert.assertTrue("package:${device.targetContext.packageName}" in result.first())
            }
        }
    }
}
