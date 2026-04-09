package com.kaspersky.kaspressample.compose_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.MainScreen
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

class SimpleComposeTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Scroll View Stub Screen") {
            MainScreen {
                flakyButton {
                    click()
                }
            }
        }

        step("Check ScrollView screen is visible") {
            CommonFlakyScreen {
                scrollView {
                    isVisible()
                }
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
                compose(timeoutMs = 16_000) {
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
                    } thenContinue {
                        // here, the context of this lambda is a context of KButton(btn5),
                        // that's why we can call KButton's methods inside the lambda
                        click()
                    }
                    or {
                        hasText(R.string.common_flaky_final_button)
                    } thenContinue {
                        // here, the context of this lambda is a context of KButton(btn5),
                        // that's why we can call KButton's methods inside the lambda
                        click()
                    }
                }
            }
        }
    }
}
