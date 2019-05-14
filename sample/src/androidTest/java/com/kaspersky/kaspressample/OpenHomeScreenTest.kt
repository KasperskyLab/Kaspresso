package com.kaspersky.kaspressample

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.screen.HomeScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class OpenHomeScreenTest : TestCase() {

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

    @Test
    fun test() {
        beforeTest {
            activityTestRule.launchActivity(null)
        }.afterTest {
            //Nothing
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
