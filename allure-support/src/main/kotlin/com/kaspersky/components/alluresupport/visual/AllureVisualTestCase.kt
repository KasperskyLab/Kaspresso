package com.kaspersky.components.alluresupport.visual

import com.kaspersky.components.alluresupport.results.AllureVisualTestFlag
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.VisualTestCase
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.model.Status
import io.qameta.allure.kotlin.model.StatusDetails

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

abstract class AllureVisualTestCase(
    private val failEarly: Boolean = false,
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.withForcedAllureSupport()
) : VisualTestCase(kaspressoBuilder = kaspressoBuilder) {

    override fun assertScreenshot(tag: String, isFullWindow: Boolean) {
        try {
            device.screenshots.assert(tag, isFullWindow)
        } catch (ex: ScreenshotsImpl.ScreenshotDoesntMatchException) {
            if (failEarly) {
                // Wrap with assertion error so test would be marked as FAILED instead of BROKEN
                // See https://github.com/allure-framework/allure-kotlin allure-kotlin-commons/src/main/kotlin/io/qameta/allure/kotlin/util/ResultsUtils.kt
                throw AssertionError(ex)
            }

            Allure.lifecycle.updateStep {
                it.status = Status.FAILED
                it.statusDetails = StatusDetails(known = true, muted = true, message = ex.message, trace = ex.stackTraceToString())
            }
            Allure.lifecycle.stopStep()
            AllureVisualTestFlag.shouldFailLate.set(true)
        }
    }
}
