package com.kaspersky.kaspresso.enricher.impl.composite

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * The implementation of the [MainSectionEnricher] interface.
 * Composes all of [MainSectionEnricher]s list into one composite [MainSectionEnricher] that is actually
 * called by [com.kaspersky.kaspresso.testcases.core.TestRunner] on each test event.
 */
class CompositeMainSectionEnricher<Data>(
    private val mainSectionEnrichers: List<MainSectionEnricher<Data>>,
    private val exceptions: MutableList<Throwable>
) : MainSectionEnricher<Data> {

    override fun TestContext<Data>.beforeMainSectionRun(testInfo: TestInfo) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.run { beforeMainSectionRun(testInfo) } }
    }

    override fun TestContext<Data>.afterMainSectionRun(testInfo: TestInfo) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.run { afterMainSectionRun(testInfo) } }
    }
}
