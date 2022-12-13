package com.kaspersky.kaspresso.sanity.device

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.File

class DeviceScreenshotSanityTest : TestCase() {

    companion object {
        private const val FULL_SCREENSHOT_TAG = "full_screenshot"
        private const val SCREENSHOT_TAG = "screenshot"
        private val MULTIPLE_TAGS = listOf("screenshot_1", "screenshot_2", "screenshot_3")
    }

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun screenshotSampleTest() {
        val screenshotDir = resourceFilesProvider.provideScreenshotFile(SCREENSHOT_TAG).parentFile
            ?: throw Exception("Invalid screenshot file, no parent dir")

        before {
            deleteDir(screenshotDir)
        }.after {
        }.run {
            Assert.assertTrue(screenshotDir.list()?.isEmpty() ?: true)
            step("Check sample screenshot sanity") {
                device.screenshots.take(SCREENSHOT_TAG)
                Assert.assertTrue(resourceFilesProvider.provideScreenshotFile(SCREENSHOT_TAG).exists())
            }
        }
    }

    @Test
    fun screenshotFullTest() {
        val screenshotDir = resourceFilesProvider.provideScreenshotFile(FULL_SCREENSHOT_TAG).parentFile
            ?: throw Exception("Invalid screenshot file, no parent dir")

        before {
            deleteDir(screenshotDir)
        }.after {
        }.run {
            Assert.assertTrue(screenshotDir.list()?.isEmpty() ?: true)
            step("Check full screenshot sanity") {
                device.screenshots.take(FULL_SCREENSHOT_TAG)
                Assert.assertTrue(resourceFilesProvider.provideScreenshotFile(FULL_SCREENSHOT_TAG).exists())
            }
        }
    }

    @Test
    fun screenshotMultipleTest() {
        val screenshotDir = resourceFilesProvider.provideScreenshotFile(FULL_SCREENSHOT_TAG).parentFile
            ?: throw Exception("Invalid screenshot file, no parent dir")

        before {
            deleteDir(screenshotDir)
        }.after {
        }.run {
            Assert.assertTrue(screenshotDir.list()?.isEmpty() ?: true)
            step("Check full screenshot sanity") {
                MULTIPLE_TAGS.forEach {
                    device.screenshots.take(it)
                }
                MULTIPLE_TAGS.forEach {
                    Assert.assertTrue(resourceFilesProvider.provideScreenshotFile(it).exists())
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
