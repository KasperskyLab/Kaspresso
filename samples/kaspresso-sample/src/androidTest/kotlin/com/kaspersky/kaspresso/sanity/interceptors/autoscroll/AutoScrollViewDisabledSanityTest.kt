package com.kaspersky.kaspresso.sanity.interceptors.autoscroll

import androidx.test.espresso.PerformException
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
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

class AutoScrollViewDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = mutableListOf()
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun click_last_button_in_ScrollView_and_then_first_one() =
        run {
            step("Open scrollView with padding screen") {
                MainScreen {
                    autoScrollScrollViewWithPaddingButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button20 in ScrollView, last item") {
                ScrollViewWithPaddingScreen {
                    Assert.assertThrows(null, PerformException::class.java) {
                        button20 {
                            click()
                        }
                    }
                    button20 {
                        isNotDisplayed()
                    }
                }
            }

            step("Click button1 in ScrollView, first item") {
                ScrollViewWithPaddingScreen {
                    button1 {
                        click()
                    }
                }
            }
        }
}
