package com.kaspersky.kaspressample.sharedtest

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.screen.SharedTestScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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

@RunWith(AndroidJUnit4::class)
class SharedSimpleFlakyTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SharedTestActivity>()

    @Test
    fun test() = run {
        step("Fill info") {
            SharedTestScreen {
                firstNameEditText {
                    replaceText("Kaspersky")
                }
                lastNameEditText {
                    replaceText("Kaspresso")
                }
                ageEditText {
                    replaceText("8")
                }
                maleButton {
                    click()
                }
            }
        }

        step("Verify Full Name info") {
            SharedTestScreen {
                firstNameEditText {
                    hasText("Kaspersky")
                }
                lastNameEditText {
                    hasText("Kaspresso")
                }
            }
        }

        step("Click on find me button") {
            SharedTestScreen {
                findMeButton {
                    click()
                }
            }
        }
    }
}
