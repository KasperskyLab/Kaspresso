package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
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

class AlertDialogTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<ComponentsActivity>()

    @Test
    fun test() = run {
        step("Open dialog") {
            ComponentsScreen {
                showDialogBtn {
                    click()
                }
            }
        }

        step("Check title and message") {
            ComponentsScreen {
                dialog {
                    title {
                        hasText("Title")
                    }

                    message {
                        hasText("Message")
                    }

                    positiveButton {
                        longClick()
                    }
                }
            }
        }

        step("Negative button click") {
            ComponentsScreen {
                showDialogBtn {
                    click()
                }

                dialog {
                    negativeButton {
                        longClick()
                    }
                }
            }
        }

        step("Neutral button click") {
            ComponentsScreen {
                showDialogBtn {
                    click()
                }

                dialog {
                    neutralButton {
                        longClick()
                    }
                }
            }
        }
    }
}
