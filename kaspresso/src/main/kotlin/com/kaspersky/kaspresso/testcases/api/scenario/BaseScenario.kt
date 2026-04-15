package com.kaspersky.kaspresso.testcases.api.scenario

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

/**
 * The base class for parametrized scenarios. A representation of some repeating steps inside the
 * [com.kaspersky.kaspresso.testcases.api.testcase.TestCase].
 *
 *  @param ScenarioData test data created in testcase's before section.
 */
abstract class BaseScenario<ScenarioData> {

    /**
     * Steps to run. Need to be implemented in derived [Scenario].
     */
    protected abstract val steps: TestContext<ScenarioData>.() -> Unit

    internal operator fun invoke(testContext: TestContext<ScenarioData>) = steps.invoke(testContext)
}
