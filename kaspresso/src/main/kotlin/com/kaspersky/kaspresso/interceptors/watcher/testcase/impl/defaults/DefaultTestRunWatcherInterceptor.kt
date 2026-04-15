package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
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

class DefaultTestRunWatcherInterceptor : TestRunWatcherInterceptor {

    private lateinit var context: BaseTestContext

    /**
     * Holds the action which will be executed before the test.
     * The action has access to BaseTestContext.
     */
    private var beforeEachTestAction: (BaseTestContext.() -> Unit)? = null

    /**
     * Holds the action which will be executed after the test.
     * The action has access to BaseTestContext.
     */
    private var afterEachTestAction: (BaseTestContext.() -> Unit)? = null

    override fun setBaseTestContext(context: BaseTestContext) {
        this.context = context
    }

    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        beforeEachTestAction?.invoke(context)
    }

    override fun onAfterSectionFinishedSuccess(testInfo: TestInfo) {
        afterEachTestAction?.invoke(context)
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        afterEachTestAction?.invoke(context)
    }

    /**
     * Set the action which will be executed before the test.
     * The action has access to BaseTestContext.
     * If you set @param override in false then the final beforeAction will be
     *     beforeAction of the parent TestCase plus current @param action.
     *     Otherwise final beforeAction will be only @param action.
     */
    fun beforeEachTest(override: Boolean = false, action: BaseTestContext.() -> Unit) {
        if (override) {
            beforeEachTestAction = action
        } else {
            val oldBeforeEachTestAction = beforeEachTestAction
            beforeEachTestAction = {
                oldBeforeEachTestAction?.invoke(this)
                action.invoke(this)
            }
        }
    }

    /**
     * Set the action which will be executed after the test.
     * The action has access to BaseTestContext.
     * If you set @param override in false then the final beforeAction will be
     *     beforeAction of the parent TestCase plus current @param action.
     *     Otherwise final beforeAction will be only @param action.
     */
    fun afterEachTest(override: Boolean = false, action: BaseTestContext.() -> Unit) {
        if (override) {
            afterEachTestAction = action
        } else {
            val oldAfterEachTestAction = afterEachTestAction
            afterEachTestAction = {
                oldAfterEachTestAction?.invoke(this)
                action.invoke(this)
            }
        }
    }
}
