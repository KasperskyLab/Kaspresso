package com.kaspersky.kaspressample

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.viewactions.orientation.Orientation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenParametrizedTest : BaseParametrizedTest() {

    private val mainScreen = MainScreen()

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
            activityTestRule.launchActivity(null)
        }.after {
            device.exploit.setOrientation(Orientation.Portrait)
        }.initialisation {
            rawData(2)
            rawData(3)
        }.transformation {
            addString("Hello world")
        }.run {
            step("Open Home Screen") {
                mainScreen {
                    descriptionText {
                        hasText(data.list.joinToString(" "))
                    }
                    nextButton {
                        click()
                    }
                }
            }
            scenario(CheckHomeTitleNoParametersScenario())
        }
    }

}