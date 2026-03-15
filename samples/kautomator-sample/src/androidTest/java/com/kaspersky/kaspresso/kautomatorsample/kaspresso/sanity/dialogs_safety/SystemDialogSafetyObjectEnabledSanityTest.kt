package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.dialogs_safety

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.kautomatorsample.systemdialogs.SystemDialogsActivity
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

class SystemDialogSafetyObjectEnabledSanityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SystemDialogsActivity>()

    @Test
    fun test() = run {

        Assume.assumeTrue(
            "No valid ways to test system dialogs on Lollipop, ignore on <23 Api",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )

        step("Click first button") {
            SystemDialogsScreen {
                flakySafely(3000) {
                    btn1 {
                        doubleClick()
                    }
                }
            }
        }
        Thread.sleep(3000L)
        step("Click second button") {
            SystemDialogsScreen {
                flakySafely(3000) {
                    btn2 {
                        isDisplayed()
                        click()
                    }
                }
            }
        }
    }
}
