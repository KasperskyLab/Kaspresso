package com.kaspersky.kaspresso.sanity.docloc

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import java.io.File

private lateinit var testDirsProvider: DirsProvider
private lateinit var testRootDirsProvider: ResourcesRootDirsProvider

private const val EN = "en"
private const val RU = "ru"
private const val SCREENSHOTS_SUBDIR = "com.kaspersky.kaspresso.sanity.docloc.DocLocSanityTest/test"
private const val PNG_EXTENSION = ".png"
private const val XML_EXTENSION = ".xml"
private val ARTIFACTS_BASE_NAMES = listOf(
    "1__Simple_screen",
    "2__Simple_fragment_-_two_buttons",
    "3__Simple_fragment_-_input",
    "4__Simple_fragment_-_typed_text",
)

/**
 * Check that doc loc tests are sane
 */
class DocLocSanityTest : DocLocScreenshotTestCase(
    locales = "en,ru",
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        testRunWatcherInterceptors.add(TestRunnerScreenshotWatcherInterceptor(screenshots))
        testDirsProvider = dirsProvider
        testRootDirsProvider = resourcesRootDirsProvider
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<SimpleActivity>()

    @ScreenShooterTest
    @Test
    fun test() = before {
        Assume.assumeTrue(
            "Due to the bug in the UiAutomation this test doesn't work and API <= 22",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )
    }.after {
        checkSanity()
        cleanUpScreenshotsDir()
    }.run {
        step("1. Launch activity") {
            activityRule.scenario.onActivity { // Test that screenshot shooter works in app's main thread
                captureScreenshot("1. Simple screen")
            }
        }

        step("2. Press Button 1") {
            SimpleScreen {
                button1 {
                    click()
                }
            }
            captureScreenshot("2. Simple fragment - two buttons")
        }

        step("3. Press Button 2") {
            SimpleScreen {
                button2 {
                    click()
                }
            }
            Screen.idle(2_500L) // Wait for timeout
            captureScreenshot("3. Simple fragment - input")
        }

        step("4. Type text") {
            SimpleScreen {
                closeSoftKeyboard()
                edit {
                    clearText()
                    typeText("Kaspresso")
                }
            }
            captureScreenshot("4. Simple fragment - typed text")
        }
    }

    private fun checkSanity() {
        val screenshotsDir = getScreenshotsRootDir()
        val screenShotsDirContent = screenshotsDir.list() ?: throw IllegalStateException("Screenshots dir should not be empty after test")
        if (screenShotsDirContent.size != 2) {
            return // kaspresso launches separate test for each locale. Can't use @After to launch after ALL tests
        }

        listOf(EN, RU).forEach {
            assertTrue("Not found '$it' locale dir under ${screenshotsDir.absolutePath}", screenShotsDirContent.contains(it))
            checkLocalizedScreenshots(getLocalizedScreenshotsDir(it))
        }
    }

    private fun checkLocalizedScreenshots(dir: File) {
        val files = getFileNamesInDir(dir)
        assertEquals("There should be 8 files: 4 screenshots and 4 xml metadata files", 8, files.size)
        ARTIFACTS_BASE_NAMES.forEach { baseName ->
            listOf(XML_EXTENSION, PNG_EXTENSION).forEach { extension ->
                dir.resolve("$baseName$extension").exists()
            }
        }
    }

    private fun getFileNamesInDir(dir: File): List<String> {
        return device.uiDevice.executeShellCommand("ls ${dir.absolutePath}")
            .split('\n')
            .filter { it.isNotEmpty() }
    }

    private fun cleanUpScreenshotsDir() {
        testDirsProvider.provideCleared(getScreenshotsRootDir())
    }

    private fun getScreenshotsRootDir(): File {
        return testDirsProvider.provideNew(testRootDirsProvider.screenshotsRootDir)
    }

    private fun getLocalizedScreenshotsDir(locale: String): File {
        if (locale !in listOf(EN, RU)) {
            throw IllegalArgumentException("Invalid locale: $locale, [$EN, $RU]")
        }

        return getScreenshotsRootDir().resolve(locale).resolve(SCREENSHOTS_SUBDIR)
    }
}
