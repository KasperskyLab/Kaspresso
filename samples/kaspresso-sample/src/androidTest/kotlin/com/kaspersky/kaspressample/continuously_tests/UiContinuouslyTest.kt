package com.kaspersky.kaspressample.continuously_tests

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.external_screens.UiContinuouslyDialogScreen
import com.kaspersky.kaspressample.external_screens.UiContinuouslyScreen
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
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

class UiContinuouslyTest : TestCase() {

    companion object {
        private const val FAKE_MAX_DELAY_MS: Long = 5_000
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testDialogPresentUntilAndroidO() {
        // Don`t allow to run this test on Android >= Oreo
        Assume.assumeTrue(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        run {

            step("Open Continuously Screen") {
                UiMainScreen {
                    continuouslyButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                UiContinuouslyScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog appeared") {
                UiContinuouslyDialogScreen {
                    dialogTitle {
                        isDisplayed()
                    }
                }
            }
        }
    }

    @Test
    fun testDialogNotPresentAfterAndroidO() {
        // Don`t allow to run this test on Android < Oreo
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

        run {

            step("Open Continuously Screen") {
                UiMainScreen {
                    continuouslyButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                UiContinuouslyScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog not appeared") {
                UiContinuouslyDialogScreen {
                    continuously(FAKE_MAX_DELAY_MS) {
                        dialogTitle {
                            isNotDisplayed()
                        }
                    }
                }
            }
        }
    }
}
