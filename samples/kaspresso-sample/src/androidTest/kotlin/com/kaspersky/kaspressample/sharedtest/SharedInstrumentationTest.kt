package com.kaspersky.kaspressample.sharedtest

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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

const val ASSERT_PACKAGE_NAME = "com.kaspersky.kaspressample"
const val CACHE_DIR_NAME = "cache"

@RunWith(AndroidJUnit4::class)
class SharedInstrumentationTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SharedTestActivity>()

    @Test
    fun test() = run {
        step("Check Application context and its some functionality") {
            val packageName = device.context.packageName
            assertTrue("Original package name == $packageName, but the expected one must contain $ASSERT_PACKAGE_NAME", packageName.contains(ASSERT_PACKAGE_NAME))
        }

        step("Check FileSystem availability through targetContext") {
            val cacheDirName = device.targetContext.applicationContext.cacheDir.name
            assertEquals("Original cache directory name == $cacheDirName, but the expected one = $CACHE_DIR_NAME", CACHE_DIR_NAME, cacheDirName)
        }
    }
}
