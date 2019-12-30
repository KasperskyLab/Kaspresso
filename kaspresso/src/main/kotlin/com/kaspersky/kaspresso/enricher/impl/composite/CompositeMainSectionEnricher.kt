package com.kaspersky.kaspresso.enricher.impl.composite

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.enricher.impl.BaseMainSectionEnricher
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * The implementation of the [MainSectionEnricher] interface.
 * Composes all of [MainSectionEnricher]s list into one composite [MainSectionEnricher] that is actually
 * called by [com.kaspersky.kaspresso.testcases.core.TestRunner] on each test event.
 */
class CompositeMainSectionEnricher<Data>(
    private val mainSectionEnrichers: List<BaseMainSectionEnricher<Data>>,
    private val exceptions: MutableList<Throwable>
) : MainSectionEnricher<Data> {

    init {
        mainSectionEnrichers.forEach { it.enrichMainSection() }
    }

    override fun beforeMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.beforeMainSectionRun(testInfo, testContext) }
    }

    override fun afterMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.afterMainSectionRun(testInfo, testContext) }
    }
}