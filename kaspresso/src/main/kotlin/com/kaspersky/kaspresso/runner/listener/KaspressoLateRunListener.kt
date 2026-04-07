package com.kaspersky.kaspresso.runner.listener

import org.junit.runner.Description

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
 * Listener added in runtime. When test tun listeners are added in runtime it won't
 * call testRunStarted and testStarted because it was added to late. Thus it's called
 * late (it's added in runtime, later than test was started) and it calls testRunStarted
 * and testStarted in lateInit method.
 *
 * Currently it used only for Allure tests
 */
interface KaspressoLateRunListener : KaspressoRunListener {

    /**
     * Call this after KaspressoLateRunListener was added to test runner to guarantee
     * the correct callbacks call order
     *
     * @param cache - callbacks that were called before KaspressoLateRunListener was added to runner
     */
    fun lateInit(cache: Cache) {
        cache.testRunStartedDescription?.let { testRunStarted(it) }
        cache.firstTestStartedDescription?.let { testStarted(it) }
    }

    /**
     * "Late" listeners don't receive first testRunStarted and testStarted calls.
     * This cache is used to store the first test and the first test run descriptions
     * to pass them into late listeners later
     */
    class Cache {
        var testRunStartedDescription: Description? = null
            private set

        var firstTestStartedDescription: Description? = null
            private set

        fun testRunStarted(description: Description) {
            if (testRunStartedDescription == null) {
                testRunStartedDescription = description
            }
        }

        fun testStarted(description: Description) {
            if (firstTestStartedDescription == null) {
                firstTestStartedDescription = description
            }
        }
    }
}
