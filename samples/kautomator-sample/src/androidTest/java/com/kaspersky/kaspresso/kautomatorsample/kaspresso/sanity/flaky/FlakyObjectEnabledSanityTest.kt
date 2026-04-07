package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.flaky

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.flaky.FlakyActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.screen.FlakyScreen
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.common.utilities.getResourceString
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

class FlakyObjectEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf(
            FlakySafeObjectBehaviorInterceptor(
                FlakySafetyParams.default(), UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG)
            )
        )
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<FlakyActivity>()

    @Test
    fun test() = run {
        step("Click actions") {
            FlakyScreen {
                btn1 {
                    hasText(getResourceString(R.string.menu_item_2))
                    click()
                }
            }
        }
    }
}
