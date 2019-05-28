package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.core.TestContext

class TestBody<BeforeSectionData, MainSectionData>(
    val testName: String,
    val beforeTestActions: () -> BeforeSectionData,
    val afterTestActions: () -> Unit,
    val mainSection: TestContext<MainSectionData>.() -> Unit,
    val mainDataProducer: ((BeforeSectionData) -> MainSectionData)?
) {
    class Builder<BeforeSectionData, MainSectionData> {
        var testName: String? = null
        var beforeTestSection: (() -> BeforeSectionData)? = null
        var afterTestSection: (() -> Unit)? = null
        var mainTestSection: (TestContext<MainSectionData>.() -> Unit)? = null
        var mainDataProducer: ((BeforeSectionData) -> MainSectionData)? = null

        fun build(): TestBody<BeforeSectionData, MainSectionData> {

            return TestBody(
                requireNotNull(testName),
                requireNotNull(beforeTestSection),
                requireNotNull(afterTestSection),
                requireNotNull(mainTestSection),
                mainDataProducer
            )
        }
    }
}