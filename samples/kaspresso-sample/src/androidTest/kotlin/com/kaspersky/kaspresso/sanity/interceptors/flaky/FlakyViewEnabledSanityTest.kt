package com.kaspersky.kaspresso.sanity.interceptors.flaky

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.FlakySafetyParams
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

class FlakyViewEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = mutableListOf(
            FlakySafeViewBehaviorInterceptor(
                FlakySafetyParams.default(),
                UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG)
            )
        )
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            MainScreen {
                flakyButton {
                    click()
                }
            }
        }

        step("Check ScrollView screen is displayed") {
            CommonFlakyScreen {
                scrollView {
                    isDisplayed()
                }
            }
        }

        step("Check btn5's text") {
            CommonFlakyScreen {
                btn5 {
                    // automate flaky safety handling is in action
                    // the text is changing during 3 seconds
                    // the default value of flaky safety timeout = 10 seconds
                    hasText(R.string.common_flaky_final_button)
                }
            }
        }
    }
}
