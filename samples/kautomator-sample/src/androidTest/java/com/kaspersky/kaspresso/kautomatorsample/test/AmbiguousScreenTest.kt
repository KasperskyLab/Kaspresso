package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.AmbiguousActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.AmbiguousScreen
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

class AmbiguousScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<AmbiguousActivity>()

    @Test
    fun ambiguousTest() {
        before {
        }.after {
        }.run {
            step("Ambiguous screen is shown") {
                AmbiguousScreen {
                    anyPossibleAmbiguousButtonByIdAndText {
                        isDisplayed()
                    }
                }
            }
            step("Click on ambiguous button") {
                AmbiguousScreen {
                    anyPossibleAmbiguousButtonByIdAndText {
                        click()
                    }
                }
            }
        }
    }
}
