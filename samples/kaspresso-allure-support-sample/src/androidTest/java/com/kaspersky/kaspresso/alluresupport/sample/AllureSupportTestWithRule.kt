package com.kaspersky.kaspresso.alluresupport.sample

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.withAllureSupport
import com.kaspersky.kaspresso.R
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcaserule.TestCaseRule
import com.kaspersky.kaspresso.alluresupport.sample.screen.MainScreen
import com.kaspersky.kaspresso.alluresupport.sample.screen.SimpleScreen
import com.kaspersky.kaspresso_sample_core.MainActivity
import org.junit.Rule
import org.junit.Test

/**
 * In this example you can observe a test tuned by default Kaspresso configuration.
 * When you start the test you can see output of default Kaspresso interceptors:
 * - a lot of useful logs
 * - failure handling
 * - screenshots in the device
 * Also you can observe the test dsl simplifying a writing of any test
 */
class AllureSupportTestWithRule {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @get:Rule
    val testCaseRule = TestCaseRule(
        testClassName = javaClass.simpleName,
        kaspressoBuilder = Kaspresso.Builder.simple().withAllureSupport()
    )

    @Test
    fun test() =
        testCaseRule.run {
            step("Open Simple Screen") {
                testLogger.i("I am testLogger")
                activityTestRule.launchActivity(null)
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    simpleButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_1 and check button_2") {
                SimpleScreen {
                    button1 {
                        click()
                    }
                    button2 {
                        isVisible()
                    }
                }
            }

            step("Click button_2 and check edit") {
                SimpleScreen {
                    button2 {
                        click()
                    }
                    edit {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText(R.string.simple_fragment_text_edittext)
                    }
                }
            }

            step("Check all possibilities of edit") {
                scenario(
                    CheckEditScenario()
                )
            }
        }
}
