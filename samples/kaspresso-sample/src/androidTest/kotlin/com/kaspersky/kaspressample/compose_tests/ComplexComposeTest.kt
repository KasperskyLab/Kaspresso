package com.kaspersky.kaspressample.compose_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.external_screens.UiComposeDialog1
import com.kaspersky.kaspressample.external_screens.UiComposeDialog2
import com.kaspersky.kaspressample.screen.ComplexComposeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ComplexComposeTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Complex compose Screen") {
            MainScreen {
                composeButton {
                    click()
                }
            }
        }

        step("Click start button") {
            ComplexComposeScreen {
                startButton {
                    isVisible()
                    click()
                }
            }
        }

        step("Handle potential unexpected behavior") {
            compose {
                // the first potential branch when ComplexComposeScreen.stage1Button is visible
                or(ComplexComposeScreen.stage1Button) {
                    isVisible()
                } then {
                    // here, there is not any special context of this lambda
                    // that's why we can't call ComplexComposeScreen.stage1Button's methods inside the lambda directly

                    // if the first branch was succeed then we execute some special flow
                    step("Flow is over the product") {
                        ComplexComposeScreen {
                            stage1Button {
                                click()
                            }
                            stage2Button {
                                isVisible()
                                click()
                            }
                        }
                    }
                }
                // the second potential branch when UiComposeDialog1.title is visible
                // just imagine that is some unexpected system or product behavior and we cannot fix it now
                or(UiComposeDialog1.title) {
                    isDisplayed()
                } then {
                    // if the second branch was succeed then we execute some special flow
                    step("Flow is over dialogs") {
                        UiComposeDialog1 {
                            okButton {
                                isDisplayed()
                                click()
                            }
                        }
                        UiComposeDialog2 {
                            title {
                                isDisplayed()
                            }
                            okButton {
                                isDisplayed()
                                click()
                            }
                        }
                    }
                }
            }
        }

        step("Click finish button") {
            ComplexComposeScreen {
                finishButton {
                    isVisible()
                    click()
                }
            }
        }
    }
}
