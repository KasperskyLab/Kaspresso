package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.MainActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.MainNativeScreen
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

/**
 * The sample of how to use native resources in combination with kAutomator
 */
class NativeSimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun nativeTest() = run {
        step("Input text in EditText and check it") {
            MainNativeScreen {
                simpleEditText {
                    replaceText("Kaspresso")
                    hasText("Kaspresso")
                }
            }
        }
        step("Type more text and check it") {
            MainNativeScreen {
                simpleEditText {
                    typeText(" is super useful")
                    hasText("Kaspresso is super useful")
                }
            }
        }
        step("Click button") {
            MainNativeScreen {
                simpleButton {
                    click()
                }
            }
        }
        step("Click checkbox and check it") {
            MainNativeScreen {
                checkBox {
                    setChecked(true)
                    isChecked()
                }
            }
        }
        step("Check headers") {
            MainNativeScreen {
                header { isDisplayed() }
                subHeader { isDisplayed() }
            }
        }
        step("Check image") {
            MainNativeScreen {
                image { isDisplayed() }
            }
        }
    }
}
