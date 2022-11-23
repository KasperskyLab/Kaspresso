package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Test

class AdbTest : TestCase() {

    @Test
    fun test() {
        val command = "undefined_command"
        try {
            adbServer.performAdb(command)
        } catch (e: AdbServerException) {
            Assert.assertTrue("unknown command $command" in e.message)
        }

        val packages = adbServer.performShell("pm list packages")
        Assert.assertTrue(device.targetContext.packageName in packages.first())

        val hostname = adbServer.performCmd("hostname")
        Assert.assertTrue(hostname.isNotEmpty())
    }
}
