package com.kaspersky.kaspresso.alluresupport.sample

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.addAllureSupport
import com.kaspersky.components.alluresupport.files.attachViewHierarchyToAllureReport
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
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            videoParams = VideoParams(bitRate = 10_000_000)
            screenshotParams = ScreenshotParams(quality = 1)
        }
    ).addAllureSupport().apply {
        testRunWatcherInterceptors.apply {
            add(object : TestRunWatcherInterceptor {
                override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
                    viewHierarchyDumper.dumpAndApply("ViewHierarchy") { attachViewHierarchyToAllureReport() }
                }
            })
        }
    }
) {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun counter() = run {
        step("Launch the app") {

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
