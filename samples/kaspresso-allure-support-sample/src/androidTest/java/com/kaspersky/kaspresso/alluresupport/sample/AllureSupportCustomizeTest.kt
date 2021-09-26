package com.kaspersky.kaspresso.alluresupport.sample

import androidx.test.rule.ActivityTestRule
import com.kaspersky.components.alluresupport.files.attachViewHierarchyToAllureReport
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.components.alluresupport.withAllureSupport
import com.kaspersky.kaspresso.alluresupport.sample.screen.MainScreen
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.ScreenshotParams
import com.kaspersky.kaspresso.params.VideoParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import org.junit.Rule
import org.junit.Test

/**
 * Use [withAllureSupport] function to add the all available allure interceptors.
 */
class AllureSupportCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withAllureSupport(
        customize = {
            videoParams = VideoParams(bitRate = 10_000_000)
            screenshotParams = ScreenshotParams(quality = 1)
        }
    ).apply {
        testRunWatcherInterceptors.apply {
            clear()
            addAll(
                listOf(
                    ScreenshotTestInterceptor(screenshots),
                    VideoRecordingTestInterceptor(videos),
                    DumpLogcatTestInterceptor(logcatDumper),
                    object : TestRunWatcherInterceptor {
                        override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
                            viewHierarchyDumper.dumpAndApply("ViewHierarchy") { attachViewHierarchyToAllureReport() }
                        }
                    }
                )
            )
        }
    }
) {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun counter() = run {
        step("Launch the app") {
            activityRule.launchActivity(null)

            MainScreen {
                incrementButton.isDisplayed()
                decrementButton.isDisplayed()
                clearButton.isDisplayed()
                valueText.isDisplayed()
            }
        }

        step("Check increase and decrease buttons") {
            step("Increase value up to five") {
                MainScreen {
                    incrementButton {
                        repeat(5) {
                            click()
                        }
                    }

                    assertValue(5)
                }
            }

            step("Decrease value down to three") {
                MainScreen {
                    decrementButton {
                        repeat(2) {
                            click()
                        }
                    }

                    assertValue(3)
                }
            }
        }

        step("Clear the value") {
            MainScreen {
                clearButton.click()
                assertValue(0)
            }
        }
    }
}
