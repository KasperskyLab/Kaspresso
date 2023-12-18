package com.kaspersky.kaspresso.sanity.interceptors.dialogs_safety

import android.os.Build
import androidx.test.espresso.NoActivityResumedException
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Assume
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
        Assume.assumeTrue(
            "No valid ways to test system dialogs on Lollipop, ignore on <23 Api",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )

        step("Open System Dialogs View Screen") {
            MainScreen {
                systemDialogsButton {
                    click()
                }
            }
        }

        step("Display Permission Window") {
            SystemDialogsScreen {
                btn1 {
                    doubleClick()
                }
            }
        }
        Thread.sleep(1000L)
        step("Try to click second button") {
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
