package com.kaspersky.kaspressample.configurator

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfiguratorSimpleTest : TestCase() {

    private val mainScreen = MainScreen()
    private val simpleScreen = SimpleScreen()

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
        }.run {

            step("Open Simple Screen") {
                mainScreen {
                    nextButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button 1 and check button 2") {
                simpleScreen {
                    button1 {
                        click()
                    }
                    button2 {
                        isVisible()
                    }
                }
            }

            step("Click button 2 and check edit") {
                simpleScreen {
                    button2 {
                        click()
                    }
                    attempt(timeoutMs = 5000) {
                        edit {
                            isVisible()
                            hasText(R.string.text_edit_text)
                        }
                    }
                }
            }

        }
    }

}