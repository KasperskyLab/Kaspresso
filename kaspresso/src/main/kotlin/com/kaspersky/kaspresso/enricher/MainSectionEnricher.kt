package com.kaspersky.kaspresso.enricher

import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
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
 * Special object for enriching 'run'-block functionality.
 * With this object you can add some additional test steps for each TestCase, that has this enricher, like this:
 *
 * <code>
 * class MyMainSectionEnricher : MainSectionEnricher<TestCaseData> {
 *
 *     override fun TestContext<TestCaseData>.beforeMainSectionRun(testInfo: TestInfo) {
 *         step("New step before 'run' block") {
 *             step("Nested step inside") {
 *                 // do additional stuff
 *             }
 *         }
 *     }
 * }
 * </code>
 *
 * @param Data - The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase].
 */
interface MainSectionEnricher<Data> {

    /**
     * This method will be invoked right before execution of "run" block in your test case.
     *
     * @param testInfo - test information, such as test identifier
     */
    fun TestContext<Data>.beforeMainSectionRun(testInfo: TestInfo) = Unit

    /**
     * This method will be invoked right after execution of "run" block in your test case.
     *
     * @param testInfo - test information, such as test identifier
     */
    fun TestContext<Data>.afterMainSectionRun(testInfo: TestInfo) = Unit
}
