package com.kaspersky.kaspressample.mainScreenTests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.external_screens.UiComposeDialog1
import com.kaspersky.kaspressample.external_screens.UiComposeDialog2
import com.kaspersky.kaspressample.screen.ChangeLocaleScreen
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.ComplexComposeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class MainScreenTest : TestCase() {
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testLocale() {

        MainScreen.changeLocaleButton {
            isVisible()
            isClickable()
            click()

        }
        ChangeLocaleScreen.text {
            isVisible()
            hasText("For en locale the value is 123")

        }
    }

    @Test
    fun testFlaky() {

        MainScreen.flakyButton {
            isVisible()
            isClickable()
            click()

        }
        CommonFlakyScreen.btn3 {
            isVisible()
            hasText("3")
            click()
        }
    }

    @Test
    fun testComplex() = run {
        step("Открыть Complex compose Screen") {
            MainScreen {
                composeButton {
                    click()
                }
            }
        }

        step("Нажать start ") {
            ComplexComposeScreen.startButton {
                isVisible()
                click()
            }
        }

        step("Нажать ОК аллерта")
        {
            UiComposeDialog1.okButton {
                isDisplayed()
                click()
            }
            UiComposeDialog2.okButton {
                isDisplayed()
                click()
            }
        }

    }
}
