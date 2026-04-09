package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
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

/**
 * The implementation of the [TestRunWatcherInterceptor] interface.
 * Takes screenshots if "before" or "after" sections failed.
 */
class TestRunnerScreenshotWatcherInterceptor(
    private val screenshots: Screenshots
) : TestRunWatcherInterceptor {

    /**
     * Takes a screenshot of the screen on which the "after" section failed.
     *
     * @param testInfo the test info to use in screenshots name.
     * @param throwable the error occurred to use in screenshots name.
     */
    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("AfterTestSection_failure_${throwable.javaClass.simpleName}")
    }

    /**
     * Takes a screenshot of the screen on which the "before" section failed.
     *
     * @param testInfo the test info to use in screenshots name.
     * @param throwable the error occurred to use in screenshots name.
     */
    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("BeforeTestSection_failure_${throwable.javaClass.simpleName}")
    }

    /**
     * Takes a screenshot of the screen on which the "main" section failed.
     *
     * @param testInfo the test info to use in screenshots name.
     * @param throwable the error occurred to use in screenshots name.
     */
    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("MainTestSection_failure_${throwable.javaClass.simpleName}")
    }
}
