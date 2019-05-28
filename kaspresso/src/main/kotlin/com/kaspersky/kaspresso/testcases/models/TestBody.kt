package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.core.TestContext

class TestBody<BeforeSectionData, MainSectionData>(
    val testName: String,
    val beforeTestActions: BeforeSectionData.() -> Unit,
    val afterTestActions: () -> Unit,
    val mainSection: TestContext<MainSectionData>.() -> Unit,
    val mainDataProducer: ((BeforeSectionData.() -> Unit) -> MainSectionData)
) {
    class Builder<BeforeSectionData, MainSectionData> {
        var testName: String? = null
        var beforeTestSection: (BeforeSectionData.() -> Unit)? = null
        var afterTestSection: (() -> Unit)? = null
        var mainTestSection: (TestContext<MainSectionData>.() -> Unit)? = null
        var mainDataProducer: ((BeforeSectionData.() -> Unit) -> MainSectionData)? = null

        fun build(): TestBody<BeforeSectionData, MainSectionData> {

            return TestBody(
                requireNotNull(testName),
                requireNotNull(beforeTestSection),
                requireNotNull(afterTestSection),
                requireNotNull(mainTestSection),
                requireNotNull(mainDataProducer)

            )
        }
    }
}