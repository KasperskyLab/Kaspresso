package com.kaspersky.kaspresso.sample_uiautomator_dsl.test

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.sample_uiautomator_dsl.MainActivity

import com.kaspersky.kaspresso.sample_uiautomator_dsl.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiSimpleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun upgradeTest() {
        before {
            activityTestRule.launchActivity(null)
        }.after {  }.run {

            step("Input text in EditText and check it") {
                MainScreen {
                    simpleEditText {
                        replaceText("Kaspresso")
                        hasText("Kaspresso")
                    }
                }
            }
            step("Click button") {
                MainScreen {
                    simpleButton {
                        click()
                        // todo put some assertion
                    }
                }
            }
            step("Click checkbox and check it") {
                MainScreen {
                    checkBox {
                        setChecked(true)
                        isChecked()
                    }
                }
            }
        }
    }
}
