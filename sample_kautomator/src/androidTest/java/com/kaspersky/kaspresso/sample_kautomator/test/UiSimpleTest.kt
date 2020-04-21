package com.kaspersky.kaspresso.sample_kautomator.test

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.kautomator.KautomatorConfigurator
import com.kaspersky.kaspresso.sample_kautomator.MainActivity

import com.kaspersky.kaspresso.sample_kautomator.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The simple sample of how Kautomator looks and
 *     its beautiful possibility to intercept all actions and assertions as Kakao does
 */
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
            KautomatorConfigurator {
                intercept {
                    onUiInteraction {
                        onCheck { uiInteraction, uiAssert ->
                            testLogger.i("KautomatorIntercept", "interaction=$uiInteraction, assertion=$uiAssert")
                            uiInteraction.check(uiAssert)
                        }
                        onPerform { uiInteraction, uiAction ->
                            testLogger.i("KautomatorIntercept", "interaction=$uiInteraction, action=$uiAction")
                        }
                    }
                }
            }

            activityTestRule.launchActivity(null)
        }.after {
        }.run {

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
