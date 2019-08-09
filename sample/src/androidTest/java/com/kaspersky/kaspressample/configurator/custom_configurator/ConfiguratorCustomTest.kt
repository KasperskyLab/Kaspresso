package com.kaspersky.kaspressample.configurator.custom_configurator

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * In this test we show how to configure your custom Kaspresso interceptors.
 * Besides in the test there are some assertions to check a work of interceptors.
 *
 */
@RunWith(AndroidJUnit4::class)
class ConfiguratorCustomTest : TestCase(
    configBuilder = Configurator.Builder.default().apply {
        viewActionInterceptors.add(CustomViewActionInterceptor())
        viewAssertionInterceptors.add(CustomViewAssertionInterceptor())
        stepInterceptors.add(CustomStepInterceptor())
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
    fun test() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
            CheckCustomInterceptorsStorage.resetAllCheckLists()
        }.run {

            step("Open Simple Screen") {
                MainScreen {
                    nextButton {
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
                        attempt(timeoutMs = 5000) { isVisible() }
                        hasText(R.string.text_edit_text)
                    }
                }
            }

            kLogger.i("Final assert")
            CheckCustomInterceptorsStorage.assertAllCheckLists(
                actionsCount = 3,
                assertionsCount = 4,
                stepCount = 3
            )
        }
    }
}