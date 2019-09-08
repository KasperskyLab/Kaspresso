package com.kaspersky.kaspressample.tests.simple

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewStubScreen
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrollViewStubTest : TestCase(
    configBuilder = Configurator.Builder.default().apply {
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

            step("Click button \"bzzz\" when it appears") {
                ScrollViewStubScreen {
                    scrollViewStub.isVisible()

                    val action: KButton.() -> Unit = {
                        hasText("zbzbz")
                        click()
                    }

                    compose {
                        or(tv6) { hasText("bzzz") }
                        or(btn1) { hasText("bzzz") }
                    }

                    compose {
                        or(btn5) { hasText("bzzz") }
                        or(btn1) { hasText("bzzz") }
                    }

                    btn5.compose {
                        or { hasText("zzzb") }
                        or {
                            hasText("bzzz")
                            click()
                        }
                        or(action)
                    }
                }
            }
        }
    }
}