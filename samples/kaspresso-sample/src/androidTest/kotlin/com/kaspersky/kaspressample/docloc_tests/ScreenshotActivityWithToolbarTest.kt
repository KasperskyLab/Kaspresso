package com.kaspersky.kaspressample.docloc_tests

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WithToolbarScreen
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

private lateinit var testDirsProvider: DirsProvider
private lateinit var testRootDirsProvider: ResourcesRootDirsProvider

class ScreenshotActivityWithToolbarTest : DocLocScreenshotTestCase(
    locales = LOCALE,
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        testDirsProvider = dirsProvider
        testRootDirsProvider = resourcesRootDirsProvider
    }
) {

    companion object {
        private const val SCREENSHOT_NAME = "screen_with_toolbar"
        private const val SCREENSHOTS_SUBDIR =
            "com.kaspersky.kaspressample.docloc_tests.ScreenshotActivityWithToolbarTest/test"
        private const val LOCALE = "en"
    }

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open screen with toolbar") {
            MainScreen {
                withToolbarButton {
                    click()
                }
            }
        }

        step("Take screenshot") {
            WithToolbarScreen {
                collapsingToolbar {
                    view.perform(ViewActions.swipeUp())
                }
            }
            captureScreenshot(SCREENSHOT_NAME)
        }

        step("Check for toolbar layout title in metadata") {
            val screenshotsRootDir = testDirsProvider.provideNew(testRootDirsProvider.screenshotsRootDir)
            val metadataFile = screenshotsRootDir.resolve("$LOCALE/$SCREENSHOTS_SUBDIR/$SCREENSHOT_NAME.xml")
            assertTrue(metadataFile.exists())

            val contents = metadataFile.readText()
            assertTrue("toolbar layout title" in contents)
        }
    }
}
