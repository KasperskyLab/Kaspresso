package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.MainActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.MainScreen
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
 * The simple sample of how Kautomator looks and
 *     its beautiful possibility to intercept all actions and assertions as Kakao does
 */
class UiSimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        before {
        }.after {
        }.run {
            step("Input text in EditText and check it") {
                MainScreen {
                    simpleEditText {
                        replaceText("Kaspresso")
                        hasText("Kaspresso")
                    }
                }
            }
            step("Type more text and check it") {
                MainScreen {
                    simpleEditText {
                        typeText(" is super useful")
                        hasText("Kaspresso is super useful")
                    }
                }
            }
            step("Click button") {
                MainScreen {
                    simpleButton {
                        click()
                    }
                }
            }
            step("Click checkbox and check it") {
                MainScreen {
                    checkBox {
                        setChecked(true)
                        isChecked()
                    }
                }
            }
        }
    }
}
