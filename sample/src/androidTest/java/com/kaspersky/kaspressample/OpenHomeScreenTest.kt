package com.kaspersky.kaspressample

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.screen.HomeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.TestCase
import com.kaspersky.kaspresso.viewactions.orientation.Orientation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenTest : TestCase() {

    private val mainScreen = MainScreen()
    private val homeScreen = HomeScreen()

    @Rule
    @JvmField
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            device.exploit.setOrientation(Orientation.Landscape)
            activityTestRule.launchActivity(null)
        }.after {
            device.exploit.setOrientation(Orientation.Portrait)
        }.run {
            step("Open Home Screen") {
                mainScreen {
                    nextButton {
                        click()
                    }
                }
            }

            step("Check Home Screen") {
                homeScreen {
                    title {
                        isVisible()
                        //hasText("Ooops!") //Uncomment to fail test
                    }
                }
            }
            step("My Awesome Scenario") {
                scenario(
                    CheckHomeTitleScenario()
                )

                homeScreen {
                    title {
                        //hasText("Ooops!") //Uncomment to fail test
                    }
                }
                step("Just Empty SubStep") {
                    step("Just Empty SubSubStep") {}
                }
            }


            step("Just Empty Step") {}
        }
    }

}