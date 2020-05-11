package com.kaspersky.kaspressample.compose_tests

import android.Manifest
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WebViewScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class WebComposeTest : TestCase() {

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

            step("Find \"Sign in\" button") {
                WebViewScreen {

                    webView {
                        withElement(
                            Locator.CLASS_NAME,
                            "btn"
                        ) {
                            containsText("Sign in")
                            web.withElement(ref).perform(DriverAtoms.getText())
                        }

                        withElement(
                            Locator.XPATH,
                            "//*[@id=\"app\"]/section[1]/div/div/h2"
                        ) {
                            containsText("Protect your data")
                        }

                        withElement(
                            Locator.XPATH,
                            "//*[@id=\"app\"]/header/section/div[3]/div[2]/button"
                        ) {
                            hasText("Sign in")
                        }
                    }
                }
            }

            step("Click \"Contacts\" button") {
                WebViewScreen {
                    webView {
                        withElement(
                            Locator.XPATH,
                            "//*[@id=\"app\"]/footer/div/div/div[1]/nav/div[1]/a"
                        ) {
                            compose(this@webView) {
                                or {
                                    containsText("fffuuuuu")
                                    hasText("fuuuu")
                                }
                                or {
                                    containsText("Ask questiop")
                                    hasText("Ask questiop")
                                }
                                or {
                                    containsText("Ask question")
                                    hasText("Ask question")
                                }
                                or {
                                    containsText("Contacts")
                                    hasText("Contacts")
                                }
                            }
                        }

                        compose {
                            orWithElement(
                                Locator.XPATH,
                                "//*[@id=\"app\"]/footer/div/div/div[1]/nav/div[1]/a"
                            ) {
                                hasText("TRATATATA")
                            }
                            orWithElement(
                                Locator.XPATH,
                                "//*[@id=\"app\"]/footer/div/div/div[1]/nav/div[1]/a"
                            ) {
                                hasText("Ask question")
                            } thenContinue {
                                click()
                            }
                            orWithElement(
                                Locator.XPATH,
                                "//*[@id=\"app\"]/footer/div/div/div[1]/nav/div[1]/a"
                            ) {
                                hasText("Contacts")
                            } thenContinue {
                                click()
                            }
                        }
                    }
                }
            }
        }
    }
}