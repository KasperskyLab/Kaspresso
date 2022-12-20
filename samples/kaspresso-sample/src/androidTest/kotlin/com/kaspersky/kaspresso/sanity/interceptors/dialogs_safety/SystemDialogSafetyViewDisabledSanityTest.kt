package com.kaspersky.kaspresso.sanity.interceptors.dialogs_safety

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class SystemDialogSafetyViewDisabledSanityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Scroll View Stub Screen") {
            MainScreen {
                systemDialogsButton {
                    click()
                }
            }
        }

        step("Check ScrollView screen is displayed") {
            SystemDialogsScreen {
                btn1 {
                    isDisplayed()
                    click()
                }
            }
        }

        step("Check ScrollView screen is displayed") {
            SystemDialogsScreen {
                btn2 {
                    isDisplayed()
                    click()
                }
            }
        }
    }
}
