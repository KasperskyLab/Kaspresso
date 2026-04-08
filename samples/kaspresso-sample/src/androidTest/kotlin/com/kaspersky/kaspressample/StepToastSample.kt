package com.kaspersky.kaspressample

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple_tests.CheckEditScenario
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.toast.ToastStepWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * This test shows how to use a ToastStepWatcherInterceptor that shows a step name in a toast. The toast will appear before each step execution.
 * toastContentProvider parameter is a lambda that takes a [com.kaspersky.kaspresso.testcases.models.info.StepInfo] and returns a CharSequence.
 * You could provide your own implementation of the content provider or use one of the predefined lambdas in the [ToastStepWatcherInterceptor.ToastContentProviders] object.
 */
class StepToastSample : TestCase(kaspressoBuilder = Kaspresso.Builder.simple().apply {
    stepWatcherInterceptors.add(ToastStepWatcherInterceptor(
        instrumentation.targetContext,
        toastContentProvider = ToastStepWatcherInterceptor.ToastContentProviders.description,
    ))
}) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Simple Screen") {
            device.screenshots.take("Additional_screenshot")
            MainScreen {
                simpleButton {
                    isVisible()
                }
            }

            step("Sub-step") {
                MainScreen {
                    simpleButton {
                        click()
                    }
                }
            }
        }

        step("Click button_1 and check button_2") {
            SimpleScreen {
                button1 {
                    click()
                }
                button2 {
                    isVisible()
                }
            }
        }

        step("Click button_2 and check edit") {
            SimpleScreen {
                button2 {
                    click()
                }
                edit {
                    flakySafely(timeoutMs = 7000) { isVisible() }
                    hasText(R.string.simple_fragment_text_edittext)
                }
            }
        }

        step("Check all possibilities of edit") {
            scenario(
                CheckEditScenario()
            )
        }
    }
}
