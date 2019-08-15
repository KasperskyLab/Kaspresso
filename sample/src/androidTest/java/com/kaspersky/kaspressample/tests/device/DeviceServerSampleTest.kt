package com.kaspersky.kaspressample.tests.device

import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspresso.device.server.AdbServer
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
        }
    }
}