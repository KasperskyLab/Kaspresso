package com.kaspersky.kaspresso.sanity.device

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.File

class ExternalScreenshotMakerSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced {
        screenshotMaker = ExternalScreenshotMaker(instrumentation)
    }
) {

    companion object {
        private const val SCREENSHOT_TAG = "external_screenshot"
        private const val FULL_SCREENSHOT_TAG = "external_full_screenshot"
    }

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun externalScreenshotTest() {
        val screenshotDir = resourceFilesProvider.provideScreenshotFile(SCREENSHOT_TAG).parentFile
            ?: throw Exception("Invalid screenshot file, no parent dir")

        before {
            deleteDir(screenshotDir)
        }.after {
        }.run {
            assertTrue(screenshotDir.list()?.isEmpty() ?: true)
            step("Take screenshot with ExternalScreenshotMaker") {
                device.screenshots.take(SCREENSHOT_TAG)
                assertTrue(resourceFilesProvider.provideScreenshotFile(SCREENSHOT_TAG).exists())
            }
        }
    }

    @Test
    fun externalFullWindowScreenshotTest() {
        val screenshotDir = resourceFilesProvider.provideScreenshotFile(FULL_SCREENSHOT_TAG).parentFile
            ?: throw Exception("Invalid screenshot file, no parent dir")

        before {
            deleteDir(screenshotDir)
        }.after {
        }.run {
            assertTrue(screenshotDir.list()?.isEmpty() ?: true)
            step("Take full window screenshot with ExternalScreenshotMaker") {
                device.screenshots.takeFullWindow(FULL_SCREENSHOT_TAG)
                assertTrue(resourceFilesProvider.provideScreenshotFile(FULL_SCREENSHOT_TAG).exists())
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
