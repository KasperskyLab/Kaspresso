package com.kaspersky.kaspressample.tests.simple

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
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
        attemptsTimeoutMs = 5_000L
    }
) {
    private val mainScreen = MainScreen()
    private val scrollViewStubScreen = ScrollViewStubScreen()

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
                mainScreen {
                    scrollViewStubButton {
                        click()
                    }
                }
            }

            step("Click button \"bzzz\" when it appears") {
                scrollViewStubScreen {
                    scrollViewStub.isVisible()

                    val action: KButton.() -> Unit = {
                        hasText("zbzbz")
                        click()
                    }

                    btn5.compose {
                        +{ it: KButton ->
                            it.hasText("bzzz")
                            it.click()
                        }
                        +{ it: KButton -> it.hasText("zzzb") }
                        +action
                    }
                }
            }
        }
    }
}