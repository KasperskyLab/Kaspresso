package com.kaspersky.kaspressample.compose_tests

import android.Manifest
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WebViewScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

class WebComposeTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {

        step("Open WebView Screen") {
            MainScreen {
                webViewButton {
                    hasAnyText()
                    click()
                }
            }
        }

        step("Find \"Interceptors\" and \"Logs\" title") {
            WebViewScreen {
                webView {
                    withElement(Locator.ID, "interceptors") {
                        containsText("Interceptors")
                    }

                    withElement(Locator.ID, "writing-readable-logs") {
                        containsText("Writing readable logs")
                    }
                }
            }
        }

        step("Find \"Kaspresso wiki\" link") {
            WebViewScreen {
                webView {
                    withElement(Locator.XPATH, "/html/body/p[39]/a") {
                        compose(this@webView) {
                            or {
                                containsText("fffuuuuu")
                                hasText("fuuuu")
                            }
                            or {
                                containsText("Kaspresso kiwi")
                                hasText("kiwi")
                            }
                            or {
                                containsText("Kaspresso wiki")
                                hasText("Kaspresso wiki")
                            }
                        }
                    }

                    compose {
                        orWithElement(Locator.XPATH, "/html/body/p[39]/a") {
                            hasText("TRATATATA")
                        }
                        orWithElement(Locator.XPATH, "/html/body/p[39]/a") {
                            hasText("Kaspresso kiwi")
                        } thenContinue {
                            click()
                        }
                        orWithElement(Locator.XPATH, "/html/body/p[39]/a") {
                            hasText("Kaspresso wiki")
                        } thenContinue {
                            // click()
                        }
                    }
                }
            }
        }
    }
}
