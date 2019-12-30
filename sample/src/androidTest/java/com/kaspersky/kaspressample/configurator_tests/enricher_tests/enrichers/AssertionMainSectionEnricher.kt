package com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspresso.enricher.impl.BaseMainSectionEnricher

class AssertionMainSectionEnricher : BaseMainSectionEnricher<EnricherTestData>() {

    override fun enrichMainSection() {
        afterMainSectionRun { testInfo ->
            step("Real step after 'run' block --> assert posts count | ${testInfo.testName}") {
                assert(data.posts.size == 2)
            }
        }
    }
}