package com.kaspersky.kaspressample.device_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.Locale
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceLanguageSampleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun languageSampleTest() {
        run {

            step("Start MainScreen in default locale") {
                MainScreen {
                    simpleButton {
                        isVisible()
                        containsText("Simple fragment")
                    }
                }
            }

            step("change language") {
                device.language.switchInApp(Locale("ru"))
                // it's so important to reload current active Activity
                // you can do it using activityTestRule or manipulating in the Application through great Kaspresso
                activityTestRule.finishActivity()
                activityTestRule.launchActivity(null)
                Thread.sleep(2_000)
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