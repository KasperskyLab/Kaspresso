package com.kaspersky.kaspressample.autoscrollfallback_tests

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewFallbackInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ScrollViewWithPaddingFailingTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        viewBehaviorInterceptors = viewBehaviorInterceptors.apply {
            val autoScrollFallback = first { it is AutoScrollViewFallbackInterceptor }
            remove(autoScrollFallback)
        }
    }
) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test1() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_18, middle item") {
                ScrollViewWithPaddingScreen {
                    button18 {
                        click()
                    }
                }
            }
        }

    @Test
    fun test2() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_20, last item") {
                ScrollViewWithPaddingScreen {
                    button20 {
                        click()
                    }
                }
            }
        }
}

