package com.kaspersky.kaspressonitrogen.enricher.impl.composite

import com.kaspersky.kaspressonitrogen.enricher.NitrogenMainSectionEnricher
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class NitrogenCompositeMainSectionEnricher<Data>(
    private val mainSectionEnrichers: List<NitrogenMainSectionEnricher<Data>>,
    private val exceptions: MutableList<Throwable>
) : NitrogenMainSectionEnricher<Data> {

    override fun NitrogenTestContext<Data>.beforeMainSectionRun(testInfo: TestInfo) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.run { beforeMainSectionRun(testInfo) } }
    }

    override fun NitrogenTestContext<Data>.afterMainSectionRun(testInfo: TestInfo) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.run { afterMainSectionRun(testInfo) } }
    }
}
