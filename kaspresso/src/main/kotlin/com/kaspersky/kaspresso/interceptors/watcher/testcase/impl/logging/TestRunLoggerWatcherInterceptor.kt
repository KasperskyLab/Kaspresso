package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
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
 * Logs [TestInfo] on each section event.
 */
class TestRunLoggerWatcherInterceptor(
    private val logger: UiTestLogger
) : TestRunWatcherInterceptor {

    /**
     * Logs the given [testInfo] on "before" section starts.
     *
     * @param testInfo the test info to log.
     */
    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        logger.section("BEFORE TEST SECTION")
    }

    /**
     * Logs the given [testInfo] on "before" section finishes with failure.
     *
     * @param testInfo the test info to log.
     * @param throwable the error occurred to log.
     */
    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        logger.section("BEFORE TEST SECTION FAILED")
    }

    /**
     * Logs the given [testInfo] on "main" section starts.
     *
     * @param testInfo the test info to log.
     */
    override fun onMainSectionStarted(testInfo: TestInfo) {
        logger.section("TEST SECTION")
    }

    /**
     * Logs the given [testInfo] on "after" section starts.
     *
     * @param testInfo the test info to log.
     */
    override fun onAfterSectionStarted(testInfo: TestInfo) {
        logger.section("AFTER TEST SECTION")
    }

    /**
     * Logs the given [testInfo] on "after" section finishes with failure.
     *
     * @param testInfo the test info to log.
     * @param throwable the error occurred to log.
     */
    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        logger.section("AFTER TEST SECTION FAILED")
    }

    /**
     * Logs the given [testInfo] on whole test finishes.
     *
     * @param testInfo the test info to log.
     * @param success the while test was finished successfully or not.
     */
    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logger.section(if (success) "TEST PASSED" else "TEST FAILED")
    }
}
