package com.kaspersky.kaspresso.sanity.interceptors.dialogs_safety

import androidx.test.espresso.NoActivityResumedException
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class SystemDialogSafetyViewDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = mutableListOf()
    }
) {

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
                    click()
                }
            }
        }

        step("Check ScrollView screen is displayed") {
            SystemDialogsScreen {
                btn2 {
                  Assert.assertThrows(null, NoActivityResumedException::class.java) {
                      isDisplayed()
                      click()
                  }
                }
            }
        }
    }
}
