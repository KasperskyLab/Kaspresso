package com.kaspersky.kaspressample.configurator_tests.enricher_tests

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data_producers.EnricherTestCaseDataProducer
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl.EnricherTestDsl
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers.AssertionMainSectionEnricher
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers.LoggingMainSectionEnricher
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

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
 * Example of base class with not default TestContext and main section enrichers.
 */
@Suppress("UnnecessaryAbstractClass")
abstract class EnricherTestCase : BaseTestCase<EnricherTestDsl, EnricherTestData>(
    kaspressoBuilder = Kaspresso.Builder.simple(),
    dataProducer = { action ->
        EnricherTestCaseDataProducer()
            .initData(action)
    },
    mainSectionEnrichers = listOf(
        LoggingMainSectionEnricher(),
        AssertionMainSectionEnricher()
    )
)
