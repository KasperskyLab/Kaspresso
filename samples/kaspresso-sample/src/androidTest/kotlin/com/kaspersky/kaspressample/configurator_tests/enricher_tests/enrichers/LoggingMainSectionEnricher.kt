package com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class LoggingMainSectionEnricher : MainSectionEnricher<EnricherTestData> {

    override fun TestContext<EnricherTestData>.beforeMainSectionRun(testInfo: TestInfo) {
        testLogger.d("Before main section run... | ${testInfo.testName}")
        testLogger.d("Check users count: ${data.users.size}")
    }

    override fun TestContext<EnricherTestData>.afterMainSectionRun(testInfo: TestInfo) {
        testLogger.d("After main section run... | ${testInfo.testName}")
        testLogger.d("Check posts count: ${data.posts.size}")
    }
}
