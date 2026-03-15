package com.kaspersky.kaspressample.docloc_tests

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WithToolbarScreen
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Assert.assertTrue
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

private lateinit var testDirsProvider: DirsProvider
private lateinit var testRootDirsProvider: ResourcesRootDirsProvider

class ScreenshotActivityWithToolbarTest : DocLocScreenshotTestCase(
    locales = LOCALE,
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        testDirsProvider = dirsProvider
        testRootDirsProvider = resourcesRootDirsProvider
    }
) {

    companion object {
        private const val SCREENSHOT_NAME = "screen_with_toolbar"
        private const val SCREENSHOTS_SUBDIR =
            "com.kaspersky.kaspressample.docloc_tests.ScreenshotActivityWithToolbarTest/test"
        private const val LOCALE = "en"
    }

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open screen with toolbar") {
            MainScreen {
                withToolbarButton {
                    click()
                }
            }
        }

        step("Take screenshot") {
            WithToolbarScreen {
                collapsingToolbar {
                    view.perform(ViewActions.swipeUp())
                }
            }
            captureScreenshot(SCREENSHOT_NAME)
        }

        step("Check for toolbar layout title in metadata") {
            val screenshotsRootDir = testDirsProvider.provideNew(testRootDirsProvider.screenshotsRootDir)
            val metadataFile = screenshotsRootDir.resolve("$LOCALE/$SCREENSHOTS_SUBDIR/$SCREENSHOT_NAME.xml")
            assertTrue(metadataFile.exists())

            val contents = metadataFile.readText()
            assertTrue("toolbar layout title" in contents)
        }
    }
}
