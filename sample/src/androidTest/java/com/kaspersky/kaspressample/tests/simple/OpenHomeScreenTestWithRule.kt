package com.kaspersky.kaspressample.tests.simple

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.HomeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcaserule.TestCaseRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenTestWithRule {

    private val mainScreen = MainScreen()
    private val homeScreen = HomeScreen()

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @get:Rule
    val testCaseRule = TestCaseRule(javaClass.simpleName)

    @Test
    fun test() {
        testCaseRule.before {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Landscape)
            activityTestRule.launchActivity(null)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        }.run {

            step("Open Home screen") {
                mainScreen {
                    nextButton {
                        click()
                    }
                }
                homeScreen {
                    title {
                        isVisible()
                        flakySafely(failureMessage = "You should've been commented this line before the test") {
                            // hasText("Ooops!") //Uncomment to fail test
                        }
                    }
                }
            }
            step("Just Empty Step") {}
        }
    }
}