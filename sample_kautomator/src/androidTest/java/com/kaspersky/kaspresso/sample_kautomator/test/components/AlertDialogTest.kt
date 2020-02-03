package com.kaspersky.kaspresso.sample_kautomator.test.components

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.sample_kautomator.ComponentsActivity
import com.kaspersky.kaspresso.sample_kautomator.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AlertDialogTest : TestCase() {

    @get:Rule
    val activityRule = ActivityTestRule(ComponentsActivity::class.java, true, true)

    @Test
    fun test() {
        run {
            step("Open dialog") {
                ComponentsScreen {
                    showDialogBtn {
                        click()
                    }
                }
            }

            step("Check title and message") {
                ComponentsScreen {
                    dialog {
                        title {
                            hasText("Title")
                        }

                        message {
                            hasText("Message")
                        }

                        positiveButton {
                            longClick()
                        }
                    }
                }
            }

            step("Negative button click") {
                ComponentsScreen {
                    showDialogBtn {
                        click()
                    }

                    dialog {
                        negativeButton {
                            longClick()
                        }
                    }
                }
            }

            step("Neutral button click") {
                ComponentsScreen {
                    showDialogBtn {
                        click()
                    }

                    dialog {
                        neutralButton {
                            longClick()
                        }
                    }
                }
            }
        }
    }
}