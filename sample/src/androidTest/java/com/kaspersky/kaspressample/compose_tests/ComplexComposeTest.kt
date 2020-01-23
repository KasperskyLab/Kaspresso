package com.kaspersky.kaspressample.compose_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.external_screens.UiComposeDialog1
import com.kaspersky.kaspressample.external_screens.UiComposeDialog2
import com.kaspersky.kaspressample.screen.ComplexComposeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComplexComposeTest : TestCase() {

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
                    or(ComplexComposeScreen.stage1Button) {
                        isVisible()
                    } then {
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
                    or(UiComposeDialog1.title) {
                        isDisplayed()
                    } then {
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
}