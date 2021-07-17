package com.kaspersky.kaspressample.device_tests

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.Locale
import org.junit.Rule
import org.junit.Test

class DeviceLanguageSampleTest : TestCase() {

    companion object {
        private const val SLEEP_TIME: Long = 2000
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    private lateinit var default: Locale

    @Test
    fun languageSampleTest() {
        before {
            default = device!!.targetContext.resources.configuration.locales[0]
        }.after {
            device!!.language.switchInApp(default)
        }.run {

            step("Change locale to english") {
                device!!.language.switchInApp(Locale.ENGLISH)
                // it's so important to reload current active Activity
                // you can do it using activityTestRule or manipulating in the Application through great Kaspresso
                activityTestRule.finishActivity()
                activityTestRule.launchActivity(null)
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
                device!!.language.switchInApp(Locale("ru"))
                // it's so important to reload current active Activity
                // you can do it using activityTestRule or manipulating in the Application through great Kaspresso
                activityTestRule.finishActivity()
                activityTestRule.launchActivity(null)
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
