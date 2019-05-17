package com.kaspersky.kaspressample

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.screen.HomeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.testcases.TestCaseRule
import com.kaspersky.kaspresso.viewactions.orientation.Orientation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenTestWithRule {

    private val mainScreen by lazy { MainScreen() }
    private val homeScreen by lazy { HomeScreen() }

    @Rule
    @JvmField
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Rule
    @JvmField
    val testCaseRule = TestCaseRule(this)

    @Test
    fun test() {
        testCaseRule.beforeTest {
            Device.exploit.setOrientation(Orientation.Landscape)
            activityTestRule.launchActivity(null)
        }.afterTest {
            Device.exploit.setOrientation(Orientation.Portrait)
        }.runSteps {

            step("Open Home screen") {
                mainScreen {
                    nextButton {
                        click()
                    }
                }
                homeScreen {
                    title {
                        isVisible()
                        //hasText("Ooops!") //Uncomment to fail test
                    }
                }
            }
            step("Just Empty Step") {}
        }
    }

}