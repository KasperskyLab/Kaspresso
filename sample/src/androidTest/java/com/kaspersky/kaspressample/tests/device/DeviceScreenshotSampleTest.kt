package com.kaspersky.kaspressample.tests.device

import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceScreenshotSampleTest : TestCase() {

    companion object {
        private const val SCREENSHOT_TAG = "screenshot"
    }

    @Test
    fun screenshotSampleTest() {
        before {
        }.after {
        }.run {
            step("Take a screenshot") {
                device.screenshots.take(SCREENSHOT_TAG)
            }
        }
    }
}