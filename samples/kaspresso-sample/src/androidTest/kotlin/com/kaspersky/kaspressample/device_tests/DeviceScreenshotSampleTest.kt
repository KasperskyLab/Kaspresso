package com.kaspersky.kaspressample.device_tests

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.File

class DeviceScreenshotSampleTest : TestCase() {

    companion object {
        private const val SCREENSHOT_TAG = "screenshot"
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun screenshotSampleTest() {
        val screenshotsDir = device.screenshots.getScreenshotDir()
        before {
            deleteDir(screenshotsDir)
        }.after {
        }.run {
            step("Take a screenshot") {
                assertTrue(screenshotsDir.list()?.isEmpty() ?: true)
                device.screenshots.takeAndApply(SCREENSHOT_TAG) {
                    assertTrue(exists())
                }
            }
        }
    }

    private fun deleteDir(dir: File?) {
        if (dir == null || !dir.exists()) return

        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) deleteDir(file)
            if (file.isFile) file.delete()
        }
        dir.delete()
    }
}
