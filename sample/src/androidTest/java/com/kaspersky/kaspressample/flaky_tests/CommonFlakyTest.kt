package com.kaspersky.kaspressample.flaky_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewStubScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrollViewStubTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.default().apply {
        flakySafetyParams.timeoutMs = 5_000L
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

            step("Click button fifth button when it appears") {
                ScrollViewStubScreen {
                    scrollViewStub.isVisible()

                    compose {
                        or(tv6) { hasText(R.string.common_flaky_final_textview) }
                        or(btn1) { hasText(R.string.common_flaky_final_textview) }
                    }

                    compose {
                        or(btn1) { hasText(R.string.common_flaky_final_button) }
                        or(btn5) { hasText(R.string.common_flaky_final_button) }
                    }

                    btn5.compose {
                        or { hasText("Something wrong") }
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