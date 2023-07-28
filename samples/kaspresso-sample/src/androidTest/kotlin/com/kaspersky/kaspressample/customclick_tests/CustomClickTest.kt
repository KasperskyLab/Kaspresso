package com.kaspersky.kaspressample.customclick_tests

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple_tests.CheckEditScenario
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.ClickParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

/**
 * The example of how to apply kakao custom clicks in Kaspresso
 */
class CustomClickTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            clickParams = ClickParams.kakaoVisual()
        }
    )
) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = before {
        Assume.assumeTrue(
            "Click visualization supported only on Android M+",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )
    }.after {
    }.run {
        step("Open Simple Screen") {
            testLogger.i("I am testLogger")
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
