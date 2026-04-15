package com.kaspersky.kaspressample.docloc_tests.advanced

import android.Manifest
import android.graphics.Color
import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.docloc.ScreenshotSampleFragment
import com.kaspersky.kaspressample.docloc.ScreenshotSampleView
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
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

/**
 * An example of advanced [DocLocScreenshotTestCase] usage.
 * For more information see DocLoc wiki page.
 */
class AdvancedScreenshotSampleTest : DocLocScreenshotTestCase(locales = "en,ru") {
    private lateinit var scenario: FragmentScenario<ScreenshotSampleFragment>

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @ScreenShooterTest
    @Test
    fun test() = before {
        Assume.assumeTrue("launchFragmentInContainer blocks UI manipulation while onFragment is open", Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
        scenario = launchFragmentInContainer()
    }.after {
    }.run {
        scenario.onFragment {
            val view = getUiSafeProxy(it as ScreenshotSampleView)
            step("1. Launch feature screen") {
                view.setCounterValue(0)
                view.setBackgroundColor(Color.WHITE)
                captureScreenshot("1. Startup")
            }

            step("2. Increase counter by 5") {
                view.setCounterValue(5)
                captureScreenshot("2. Value has been increased by 5")
            }

            step("3. Set red background color") {
                view.setBackgroundColor(Color.RED)
                captureScreenshot("3. Background has been set to red")
            }
        }
    }
}
