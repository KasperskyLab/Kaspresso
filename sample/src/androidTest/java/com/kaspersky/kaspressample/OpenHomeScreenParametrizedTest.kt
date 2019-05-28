package com.kaspersky.kaspressample

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.screen.BaseParametrizedTest
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.viewactions.orientation.Orientation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenParametrizedTest : BaseParametrizedTest() {

    private val mainScreen by lazy { MainScreen() }

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
        beforeTest {
            rawData(2)
            rawData(3)
            activityTestRule.launchActivity(null)

        }.afterTest {
            Device.exploit.setOrientation(Orientation.Portrait)
        }.runSteps {

            step("Open Home Screen $data") {
                mainScreen {
                    nextButton {
                        click()
                    }
                }
            }
        }
    }

}