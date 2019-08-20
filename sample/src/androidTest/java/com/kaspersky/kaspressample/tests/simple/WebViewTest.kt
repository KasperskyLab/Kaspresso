package com.kaspersky.kaspressample.tests.simple

import android.Manifest
import android.support.test.espresso.web.webdriver.DriverAtoms
import android.support.test.espresso.web.webdriver.Locator
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WebViewScreen
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.concurrent.TimeUnit
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WebViewTest : TestCase() {

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

            step("Open WebView Screen") {
                MainScreen {
                    webViewButton {
                        hasAnyText()
                        click()
                    }
                }
            }

            step("Click \"Sign in\" button") {
                WebViewScreen {

                    webView {
                        attempt(
                            timeoutMs = TimeUnit.SECONDS.toMillis(3)
                        ) {
                            withElement(
                                Locator.XPATH,
                                "/html/body/header/section/div[1]/div/div[1]/div[2]/button[3]"
                            ) {
                                hasText("Sign up")
                            }
                        }

                        withElement(
                            Locator.CLASS_NAME,
                            "btn"
                        ) {
                            containsText("Sign in")
                            web.withElement(ref).perform(DriverAtoms.getText())
                        }

                        // same element
                        withElement(
                            Locator.XPATH,
                            "/html/body/header/section/div[3]/div[2]/button"
                        ) {
                            hasText("Sign in")
                            scroll()
                            click()
                        }
                    }
                }
            }
        }
    }
}