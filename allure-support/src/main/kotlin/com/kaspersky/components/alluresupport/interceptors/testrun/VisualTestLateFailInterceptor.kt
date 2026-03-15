package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.components.alluresupport.results.AllureVisualTestFlag
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

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

class VisualTestLateFailInterceptor : TestRunWatcherInterceptor {
    override fun onAfterSectionStarted(testInfo: TestInfo) {
        if (AllureVisualTestFlag.shouldFailLate.get()) {
            // Wrap with assertion error so test would be marked as FAILED instead of BROKEN
            // See https://github.com/allure-framework/allure-kotlin allure-kotlin-commons/src/main/kotlin/io/qameta/allure/kotlin/util/ResultsUtils.kt
            throw AssertionError(ScreenshotsImpl.ScreenshotDoesntMatchException("There were failed screenshot comparisons. Check the allure report"))
        }
    }
}
