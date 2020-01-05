package com.kaspersky.kaspressample.flaky_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.concurrent.TimeUnit
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced().apply {
        flakySafetyParams.timeoutMs = TimeUnit.SECONDS.toMillis(8)
    }
) {

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
            activityTestRule.launchActivity(null)
        }.after {
        }.run {
            step("Open Scroll View Stub Screen") {
                MainScreen {
                    scrollViewStubButton {
                        click()
                    }
                }
            }

            step("Check ScrollView screen is visible") {
                CommonFlakyScreen {
                    scrollView.isVisible()
                }
            }

            step("Check btn5's text") {
                CommonFlakyScreen {
                    compose {
                        or(btn5) { hasText(R.string.common_flaky_final_button) }
                        or(btn1) { hasText(R.string.common_flaky_final_button) }
                    }
                }
            }

            step("Check tv6's text") {
                CommonFlakyScreen {
                    compose {
                        or(tv6) { hasText(R.string.common_flaky_final_textview) }
                        or(btn1) { hasText(R.string.common_flaky_final_textview) }
                    }
                }
            }

            step("Check btn5's text again and click on it") {
                CommonFlakyScreen {
                    btn5.compose {
                        or {
                            hasText("Something wrong")
                            click()
                        }
                        or {
                            hasText(R.string.common_flaky_final_button)
                            click()
                        }
                    }
                }
            }
        }
    }
}