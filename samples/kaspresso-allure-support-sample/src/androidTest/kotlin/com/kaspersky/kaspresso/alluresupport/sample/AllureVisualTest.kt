package com.kaspersky.kaspresso.alluresupport.sample

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.visual.AllureVisualTestCase
import com.kaspersky.kaspresso.alluresupport.sample.screen.MainScreen
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

class AllureVisualTest : AllureVisualTestCase(failEarly = false) {
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Test
    fun counter() = runScreenshotTest {
        step("Assert screenshot") {
            assertScreenshot("some_tag")
        }

        step("Usual checks") {
            MainScreen {
                incrementButton { isDisplayed() }
                decrementButton { isDisplayed() }
                clearButton { isDisplayed() }
                valueText { isDisplayed() }
            }
        }
    }
}
