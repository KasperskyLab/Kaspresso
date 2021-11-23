package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AlertDialogTest : TestCase() {

    @get:Rule
    val activityRule = ActivityTestRule(ComponentsActivity::class.java, true, true)

    private val componentsScreen = ComponentsScreen()

    @Test
    fun test() {
        run {
            step("Open dialog") {
                componentsScreen {
                    showDialogBtn {
                        click()
                    }
                }
            }

            step("Check title and message") {
                componentsScreen {
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
                componentsScreen {
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
                componentsScreen {
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
