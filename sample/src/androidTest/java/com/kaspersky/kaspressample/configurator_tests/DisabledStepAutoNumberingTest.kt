package com.kaspersky.kaspressample.configurator_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisabledStepAutoNumberingTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced().apply {
        stepParams.autonumber = false
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
                    button1.click()
                    button2.isVisible()
                }
            }

            step("Click button 2 and check edit") {
                SimpleScreen {
                    button2.click()
                    edit {
                        flakySafely(timeoutMs = 5000) { isVisible() }
                        hasText(R.string.simple_fragment_text_edittext)
                    }
                }
            }

            testLogger.i("Final assert")
        }
    }
}