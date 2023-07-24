package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.dialogs_safety

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.kautomatorsample.systemdialogs.SystemDialogsActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class SystemDialogSafetyObjectDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf()
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<SystemDialogsActivity>()

    @Test
    fun test() = run {

        Assume.assumeTrue(
            "No valid ways to test system dialogs on Lollipop, ignore on <23 Api",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )

        step("Click first button") {
            SystemDialogsScreen {
                btn1 {
                    doubleClick()
                }
            }
        }
        Thread.sleep(1000L)
        step("Click second button") {
            SystemDialogsScreen {
                btn2 {
                    Assert.assertThrows(null, StaleObjectException::class.java) {
                        isDisplayed()
                        click()
                    }
                }
            }
        }
    }
}
