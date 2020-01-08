package com.kaspersky.kaspressample.configurator_tests.enricher_tests

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data_producers.EnricherTestCaseDataProducer
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl.EnricherTestDsl
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers.AssertionMainSectionEnricher
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers.LoggingMainSectionEnricher
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

/**
 * Example of base class with not default TestContext and main section enrichers.
 */
abstract class EnricherTestCase : BaseTestCase<EnricherTestDsl, EnricherTestData>(
    kaspressoBuilder = Kaspresso.Builder.advanced(),
    dataProducer = { action ->
        EnricherTestCaseDataProducer()
            .initData(action)
    },
    mainSectionEnrichers = listOf(
        LoggingMainSectionEnricher(),
        AssertionMainSectionEnricher()
    )
)