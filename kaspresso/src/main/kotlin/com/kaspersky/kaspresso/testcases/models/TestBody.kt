package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.TestContext

class TestBody<BeforeSectionData, MainSectionData>(
    val testName: String,
    val beforeTestActions: BaseTestContext.() -> Unit,
    val afterTestActions: BaseTestContext.() -> Unit,
    val mainSection: TestContext<MainSectionData>.() -> Unit,
    var initialisationSection: (BeforeSectionData.() -> Unit)?,
    val transformationsList: List<MainSectionData.() -> Unit>,
    val mainDataProducer: (((BeforeSectionData.() -> Unit)?) -> MainSectionData)
) {
    class Builder<BeforeSectionData, MainSectionData> {
        var testName: String? = null
        var beforeTestSection: (BaseTestContext.() -> Unit)? = null
        var afterTestSection: (BaseTestContext.() -> Unit)? = null
        var mainTestSection: (TestContext<MainSectionData>.() -> Unit)? = null
        var initialisationSection: (BeforeSectionData.() -> Unit)? = null
        val conditionSectionsList: MutableList<MainSectionData.() -> Unit> = mutableListOf()
        var mainDataProducer: (((BeforeSectionData.() -> Unit)?) -> MainSectionData)? = null

        fun build(): TestBody<BeforeSectionData, MainSectionData> {

            checkInitialisationInvariants()

            return TestBody(
                requireNotNull(testName),
                requireNotNull(beforeTestSection),
                requireNotNull(afterTestSection),
                requireNotNull(mainTestSection),
                initialisationSection,
                conditionSectionsList,
                requireNotNull(mainDataProducer)

            )
        }

        private fun checkInitialisationInvariants() {
            if (conditionSectionsList.isNotEmpty() && initialisationSection == null) {
                throw IllegalArgumentException("Initialisation section can not be empty for non empty transformation section")
            }
        }
    }
}