package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.autoscroll

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.autoscroll.AutoScrollActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.AutoScrollScreen
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.AutoScrollParams
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

class AutoScrollObjectEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf(AutoScrollObjectBehaviorInterceptor(UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG), AutoScrollParams.default()))
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<AutoScrollActivity>()

    @Test
    fun test() = run {
        step("Click actions") {
            AutoScrollScreen {
                button1 {
                    click()
                }
                button20 {
                    click()
                }
            }
        }
    }
}
