package com.kaspersky.kaspressample.configurator_tests.interceptor_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.helpers.CheckCustomInterceptorsStorage
import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.interceptors.CustomStepWatcherInterceptor
import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.interceptors.CustomViewActionWatcherInterceptor
import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.interceptors.CustomViewAssertionWatcherInterceptor
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * In this test we show how to configure your custom Kaspresso interceptors.
 * Besides in the test there are some assertions to check a work of interceptors.
 *
 */
class KaspressoConfiguringTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        viewActionWatcherInterceptors.add(CustomViewActionWatcherInterceptor())
        viewAssertionWatcherInterceptors.add(CustomViewAssertionWatcherInterceptor())
        stepWatcherInterceptors.add(CustomStepWatcherInterceptor())
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
    fun test() {
        before {
        }.after {
            CheckCustomInterceptorsStorage.resetAllCheckLists()
        }.run {

            step("Open Simple Screen") {
                MainScreen {
                    simpleButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button 1 and check button 2") {
                SimpleScreen {
                    button1 {
                        click()
                    }
                    button2 {
                        isVisible()
                    }
                }
            }

            step("Click button 2 and check edit") {
                SimpleScreen {
                    button2 {
                        click()
                    }
                    edit {
                        flakySafely(timeoutMs = 5000) { isVisible() }
                        hasText(R.string.simple_fragment_text_edittext)
                    }
                }
            }

            testLogger.i("Final assert")
            CheckCustomInterceptorsStorage.assertAllCheckLists(
                actionsCount = 3,
                assertionsCount = 4,
                stepCount = 3
            )
        }
    }
}
