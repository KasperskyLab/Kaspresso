package com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspresso.enricher.impl.BaseMainSectionEnricher

class LoggingMainSectionEnricher : BaseMainSectionEnricher<EnricherTestData>() {

    override fun enrichMainSection() {
        beforeMainSectionRun { testInfo ->
            testLogger.d("Before main section run... | ${testInfo.testName}")
            testLogger.d("Check users count: ${data.users.size}")
        }

        afterMainSectionRun { testInfo ->
            testLogger.d("After main section run... | ${testInfo.testName}")
            testLogger.d("Check posts count: ${data.posts.size}")
        }
    }
}