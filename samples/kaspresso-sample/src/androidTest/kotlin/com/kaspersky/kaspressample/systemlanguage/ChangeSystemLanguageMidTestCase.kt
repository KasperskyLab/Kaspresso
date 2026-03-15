package com.kaspersky.kaspressample.systemlanguage

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.ChangeLocaleScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import java.util.Locale

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

class ChangeSystemLanguageMidTestCase : TestCase() {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkLocaleChangeMidTest() = run {
        step("Open change locale screen") {
            MainScreen {
                changeLocaleButton { click() }
            }
        }
        step("Check EN locale text") {
            device.language.switchInSystem(Locale.ENGLISH)
            ChangeLocaleScreen {
                text { containsText("123") }
            }
        }
        step("Check RU locale text") {
            device.language.switchInSystem(Locale.forLanguageTag("RU"))
            ChangeLocaleScreen {
                text { containsText("321") }
            }
        }
    }
}
