package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults.DefaultTestRunWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
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
 * Composes all of [TestRunWatcherInterceptor]s list into one composite [TestRunWatcherInterceptor] that is actually
 * called by [com.kaspersky.kaspresso.testcases.core.TestRunner] on each test event.
 */
class TestRunCompositeWatcherInterceptor(
    private val watcherInterceptors: List<TestRunWatcherInterceptor>,
    private val logger: Logger,
    private val exceptions: MutableList<Throwable>
) : TestRunWatcherInterceptor {

    companion object {
        const val BIGGER: Int = -1
        const val FEWER: Int = 1
        const val DOES_NOT_MATTER: Int = 0
    }

    override fun setBaseTestContext(context: BaseTestContext) {
        watcherInterceptors.forEachSafely(exceptions) { it.setBaseTestContext(context) }
    }

    /**
     * Called on the whole test starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onTestStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onTestStarted(testInfo) }
    }

    /**
     * Called on "before" section starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        checkIfDefaultWatcherInterceptorsExist()
        getBeforeSectionList().forEachSafely(exceptions) { it.onBeforeSectionStarted(testInfo) }
    }

    private fun checkIfDefaultWatcherInterceptorsExist() {
        if (watcherInterceptors
                .filterIsInstance<DefaultTestRunWatcherInterceptor>()
                .isEmpty()
        ) {
            logger.e(
                """Revert back DefaultTestRunWatcherInterceptor to
                        Kaspresso.Builder.testRunWatcherInterceptors.
                        Otherwise Kaspresso.Builder.beforeEachTest and Kaspresso.Biulder.afterEachTest will not work!
                        """.trimIndent()
            )
        }
    }

    /**
     * First of all, call DefaultTestRunWatcherInterceptor because it's beforeEachTest is very first in the test
     */
    private fun getBeforeSectionList(): List<TestRunWatcherInterceptor> {
        return watcherInterceptors.sortedWith(
            Comparator { o1, o2 ->
                when {
                    o1 is DefaultTestRunWatcherInterceptor -> return@Comparator BIGGER
                    o2 is DefaultTestRunWatcherInterceptor -> return@Comparator FEWER
                    else -> return@Comparator DOES_NOT_MATTER
                }
            }
        )
    }

    /**
     * Called on "before" section finishes with success, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onBeforeSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedSuccess(testInfo) }
    }

    /**
     * Called on "before" section finishes with failure, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param throwable the error occured to pass to  [watcherInterceptors].
     */
    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) {
            it.onBeforeSectionFinishedFailed(
                testInfo,
                throwable
            )
        }
    }

    /**
     * Called on "main" section starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onMainSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onMainSectionStarted(testInfo) }
    }

    /**
     * Called on "main" section finishes with success, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onMainSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onMainSectionFinishedSuccess(testInfo) }
    }

    /**
     * Called on "main" section finishes with failure, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param throwable the error occured to pass to  [watcherInterceptors].
     */
    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) {
            it.onMainSectionFinishedFailed(
                testInfo,
                throwable
            )
        }
    }

    /**
     * Called on "after" section starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onAfterSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onAfterSectionStarted(testInfo) }
    }

    /**
     * Called on "after" section finishes with success, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun onAfterSectionFinishedSuccess(testInfo: TestInfo) {
        checkIfDefaultWatcherInterceptorsExist()
        getAfterSectionList().forEachSafely(exceptions) { it.onAfterSectionFinishedSuccess(testInfo) }
    }

    /**
     * Called on "after" section finishes with failure, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param throwable the error occured to pass to  [watcherInterceptors].
     */
    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        checkIfDefaultWatcherInterceptorsExist()
        getAfterSectionList().forEachSafely(exceptions) {
            it.onAfterSectionFinishedFailed(
                testInfo,
                throwable
            )
        }
    }

    /**
     * In last turn, call DefaultTestRunWatcherInterceptor because it's afterEachTest is very last in the test
     */
    private fun getAfterSectionList(): List<TestRunWatcherInterceptor> {
        return watcherInterceptors.sortedWith(
            Comparator { o1, o2 ->
                when {
                    o1 is DefaultTestRunWatcherInterceptor -> return@Comparator FEWER
                    o2 is DefaultTestRunWatcherInterceptor -> return@Comparator BIGGER
                    else -> return@Comparator DOES_NOT_MATTER
                }
            }
        )
    }

    /**
     * Called on the whole test finishes, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param success the while test was finished successfully or not, to pass to [watcherInterceptors].
     */
    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        watcherInterceptors.forEachSafely(exceptions) { it.onTestFinished(testInfo, success) }
    }
}
