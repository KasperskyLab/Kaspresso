package com.kaspersky.kaspresso.testcases.core.sections

import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

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

interface TransformSection<Data> {

    /**
     * Runs:
     * 1) Optional [BeforeTestSection],
     * 2) Optional [InitSection.init],
     * 3) Optional [transform]'s sections (only if [InitSection.init] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [BeforeTestSection] and [InitSection.init] but before [MainTestSection].
     * It's possible to add multiple transform blocks.
     *
     * @param actions actions to run.
     * @return [TransformSection] to continue building a test.
     */
    fun transform(actions: Data.() -> Unit): TransformSection<Data>
}
