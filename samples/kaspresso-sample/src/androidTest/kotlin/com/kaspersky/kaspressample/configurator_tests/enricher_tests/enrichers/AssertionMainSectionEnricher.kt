package com.kaspersky.kaspressample.configurator_tests.enricher_tests.enrichers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class AssertionMainSectionEnricher : MainSectionEnricher<EnricherTestData> {

    override fun TestContext<EnricherTestData>.afterMainSectionRun(testInfo: TestInfo) {
        step("Real step after 'run' block --> assert posts count | ${testInfo.testName}") {
            assert(data.posts.size == 2)
        }
    }
}
