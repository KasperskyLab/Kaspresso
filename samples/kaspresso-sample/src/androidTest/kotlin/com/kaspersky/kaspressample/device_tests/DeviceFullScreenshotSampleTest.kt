package com.kaspersky.kaspressample.device_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.devicefull.DeviceFullWindowSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.File

class DeviceFullScreenshotSampleTest : TestCase() {

    companion object {
        private const val SCREENSHOT_TAG = "screenshot_full"
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceFullWindowSampleActivity>()

    @Test
    fun screenshotSampleTest() {
        val screenshotDir = resourceFilesProvider.provideScreenshotFile(SCREENSHOT_TAG).parentFile
            ?: throw Exception("Invalid screenshot file, no parent dir")

        before {
            deleteDir(screenshotDir)
        }.after {
        }.run {
            step("Take a screenshot") {
                Assert.assertTrue(screenshotDir.list()?.isEmpty() ?: true)
                device.screenshots.takeFullWindow(SCREENSHOT_TAG)
                Assert.assertTrue(resourceFilesProvider.provideScreenshotFile(SCREENSHOT_TAG).exists())
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
