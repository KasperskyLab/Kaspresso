package com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo


class LoggingMainSectionEnricher : MainSectionEnricher<EnricherTestData> {

    override fun beforeMainSectionRun(testInfo: TestInfo, testContext: TestContext<EnricherTestData>) {
        testContext.testLogger.d("Before main section run...")
        testContext.testLogger.d("Check users count: ${testContext.data.users.size}")
    }

    override fun afterMainSectionRun(testInfo: TestInfo, testContext: TestContext<EnricherTestData>) {
        testContext.testLogger.d("After main section run...")
        testContext.testLogger.d("Check posts count: ${testContext.data.posts.size}")
    }

}