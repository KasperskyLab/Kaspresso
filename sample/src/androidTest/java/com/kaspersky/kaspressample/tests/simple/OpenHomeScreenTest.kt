package com.kaspersky.kaspressample.tests.simple

import android.Manifest
import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.text.TextViewActions
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.scenarios.CheckHomeTitleScenario
import com.kaspersky.kaspressample.screen.HomeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenTest : TestCase() {

    private val mainScreen = MainScreen()
    private val homeScreen = HomeScreen()

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Landscape)
            activityTestRule.launchActivity(null)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
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
                        // hasText("Ooops!") //Uncomment to fail test
                    }
                }
            }
            step("My Awesome Scenario") {
                scenario(
                    CheckHomeTitleScenario()
                )

                homeScreen {
                    title {
                        // hasText("Ooops!") //Uncomment to fail test
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

/**
 * @return a [String] descriotion of [TextViewActions].
 */
fun TextViewActions.getText(): String {

    var stringHolder = "_"

    view.perform(object : ViewAction {

        override fun getConstraints() = ViewMatchers.isAssignableFrom(TextView::class.java)

        override fun getDescription() = "getting text from a TextView"

        override fun perform(uiController: UiController?, view: View?) {
            val tv = view as TextView
            stringHolder = tv.text.toString()
        }
    })

    return stringHolder
}