package com.kaspersky.kaspressample.measure_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.external_screens.UiCommonFlakyScreen
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.external_screens.UiSimpleScreen
import com.kaspersky.kaspressample.external_screens.UiWaitForIdleScreen
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KautomatorMeasureTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced {
        kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.boost()
    }
) {

    companion object {
        private val RANGE = 0..20
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        before {
            activityTestRule.launchActivity(null)
        }.after {}.run {
            step("MainScreen. Check `simple fragment` button existence and click") {
                UiMainScreen {
                    simpleButton {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.main_screen_simple_fragment_button).toUpperCase())
                        click()
                    }
                }
            }

            step("Simple fragment. Work with buttons") {
                UiSimpleScreen {
                    button1 {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.simple_fragment_text_button_1).toUpperCase())
                        click()
                    }
                    button2 {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.simple_fragment_text_button_2).toUpperCase())
                        click()
                    }
                }
            }

            step("Simple fragment. Work with EditText in the cycle") {
                UiSimpleScreen {
                    edit {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.simple_fragment_text_edittext))
                        (RANGE).forEach { _ ->
                            clearText()
                            typeText("bla-bla-bla")
                            hasText("bla-bla-bla")
                            clearText()
                            typeText("mo-mo-mo")
                            hasText("mo-mo-mo")
                            clearText()
                        }
                    }
                }
            }

            step("Return to MainScreen") {
                UiSimpleScreen {
                    pressBack()
                }
            }

            step("MainScreen. Check `flaky sample` button existence and click") {
                UiMainScreen {
                    flakyButton {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.main_screen_scroll_view_sample_button).toUpperCase())
                        click()
                    }
                }
            }

            step("FlakyScreen. Check btn5") {
                UiCommonFlakyScreen {
                    btn5 {
                        isDisplayed()
                        hasText("5")
                    }
                }
            }

            step("Return to MainScreen") {
                UiCommonFlakyScreen {
                    pressBack()
                }
            }

            step("MainScreen. Check `waiting for idle sample` button existence and click") {
                UiMainScreen {
                    idleWaitingButton {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.main_screen_idlewaiting_sample_button).toUpperCase())
                        click()
                    }
                }
            }

            step("UiWaitForIdleScreen. Check text in EditText") {
                UiWaitForIdleScreen {
                    edit {
                        isDisplayed()
                        containsText(device.targetContext.getString(R.string.idlewaiting_fragment_text_edittext))
                    }
                }
            }
        }
}