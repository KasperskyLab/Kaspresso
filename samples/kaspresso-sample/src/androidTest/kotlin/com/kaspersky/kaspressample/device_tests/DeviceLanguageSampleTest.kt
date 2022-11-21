package com.kaspersky.kaspressample.device_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import java.util.Locale

class DeviceLanguageSampleTest : TestCase() {

    companion object {
        private const val SLEEP_TIME: Long = 2000
    }

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private lateinit var default: Locale

    @Test
    fun languageSampleTest() {
        before {
            default = device.targetContext.resources.configuration.locales[0]
        }.after {
            device.language.switchInApp(default)
        }.run {

            step("Change locale to english") {
                device.language.switchInApp(Locale.ENGLISH)
                // it's so important to reload current active Activity
                // you can do it recreating the activity or manipulating in the Application through great Kaspresso
                activityRule.scenario.onActivity { activity ->
                    activity.recreate()
                }
                Thread.sleep(SLEEP_TIME)
            }

            step("Start MainScreen in default locale") {
                MainScreen {
                    simpleButton {
                        isVisible()
                        containsText("Simple fragment")
                    }
                }
            }

            step("Change locale to russian") {
                device.language.switchInApp(Locale("ru"))
                // it's so important to reload current active Activity
                // you can do it recreating the activity or manipulating in the Application through great Kaspresso
                activityRule.scenario.onActivity { activity ->
                    activity.recreate()
                }
                Thread.sleep(SLEEP_TIME)
            }

            step("Start MainScreen in russian locale") {
                MainScreen {
                    simpleButton {
                        isVisible()
                        containsText("Простой пример")
                    }
                }
            }
        }
    }
}
