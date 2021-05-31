package com.kaspersky.kaspressonitrogen.testcases.models

import com.kaspersky.kaspressonitrogen.enricher.NitrogenMainSectionEnricher
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext

internal class NitrogenTestBody<InitData, Data>(
    val testName: String,
    val beforeTestActions: (NitrogenBaseTestContext.() -> Unit)?,
    val afterTestActions: (NitrogenBaseTestContext.() -> Unit)?,
    val steps: NitrogenTestContext<Data>.() -> Unit,
    val initActions: (InitData.() -> Unit)?,
    val transformActionsList: List<Data.() -> Unit>,
    val dataProducer: (((InitData.() -> Unit)?) -> Data),
    val mainSectionEnrichers: List<NitrogenMainSectionEnricher<Data>>
) {
    internal class Builder<InitData, Data> {
        var testName: String? = null
        var beforeTestActions: (NitrogenBaseTestContext.() -> Unit)? = null
        var afterTestActions: (NitrogenBaseTestContext.() -> Unit)? = null
        var steps: (NitrogenTestContext<Data>.() -> Unit)? = null
        var initActions: (InitData.() -> Unit)? = null
        val transformActionsList: MutableList<Data.() -> Unit> = mutableListOf()
        var dataProducer: (((InitData.() -> Unit)?) -> Data)? = null
        var mainSectionEnrichers: List<NitrogenMainSectionEnricher<Data>> = mutableListOf()

        fun build(): NitrogenTestBody<InitData, Data> {
            checkInitializationInvariants()

            return NitrogenTestBody(
                requireNotNull(testName),
                beforeTestActions,
                afterTestActions,
                requireNotNull(steps),
                initActions,
                transformActionsList,
                requireNotNull(dataProducer),
                mainSectionEnrichers
            )
        }

        private fun checkInitializationInvariants() {
            if (transformActionsList.isNotEmpty() && initActions == null) {
                throw IllegalArgumentException("Init data section can not be empty for non empty transform data section.")
            }
        }
    }
}
