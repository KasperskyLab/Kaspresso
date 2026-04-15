package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
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

internal class TestBody<InitData, Data>(
    val testName: String,
    val beforeTestActions: (BaseTestContext.() -> Unit)?,
    val afterTestActions: (BaseTestContext.() -> Unit)?,
    val steps: TestContext<Data>.() -> Unit,
    val initActions: (InitData.() -> Unit)?,
    val transformActionsList: List<Data.() -> Unit>,
    val dataProducer: (((InitData.() -> Unit)?) -> Data),
    val mainSectionEnrichers: List<MainSectionEnricher<Data>>
) {
    internal class Builder<InitData, Data> {
        var testName: String? = null
        var beforeTestActions: (BaseTestContext.() -> Unit)? = null
        var afterTestActions: (BaseTestContext.() -> Unit)? = null
        var steps: (TestContext<Data>.() -> Unit)? = null
        var initActions: (InitData.() -> Unit)? = null
        val transformActionsList: MutableList<Data.() -> Unit> = mutableListOf()
        var dataProducer: (((InitData.() -> Unit)?) -> Data)? = null
        var mainSectionEnrichers: List<MainSectionEnricher<Data>> = mutableListOf()

        fun build(): TestBody<InitData, Data> {
            checkInitializationInvariants()

            return TestBody(
                requireNotNull(testName),
                beforeTestActions,
                afterTestActions,
                requireNotNull(steps),
                initActions,
                transformActionsList,
                requireNotNull(dataProducer),
                mainSectionEnrichers
            )
        }

        private fun checkInitializationInvariants() {
            if (transformActionsList.isNotEmpty() && initActions == null) {
                throw IllegalArgumentException("Init data section can not be empty for non empty transform data section.")
            }
        }
    }
}
