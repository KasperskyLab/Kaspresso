package com.kaspersky.kaspresso.interceptors.watcher.testcase

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

/**
 * The interface for all interceptors intercepting test run events.
 */
interface TestRunWatcherInterceptor : TestContextHolder {

    fun onTestStarted(testInfo: TestInfo) = Unit

    fun onBeforeSectionStarted(testInfo: TestInfo) = Unit
    fun onBeforeSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onMainSectionStarted(testInfo: TestInfo) = Unit
    fun onMainSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onAfterSectionStarted(testInfo: TestInfo) = Unit
    fun onAfterSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onTestFinished(testInfo: TestInfo, success: Boolean) = Unit
}
