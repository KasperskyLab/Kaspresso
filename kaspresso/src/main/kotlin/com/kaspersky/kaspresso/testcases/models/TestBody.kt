package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.core.TestContext

class TestBody(
    val testName: String,
    val beforeTestActions: () -> Unit,
    val afterTestActions: () -> Unit,
    val mainSection: TestContext.() -> Unit
) {
    class Builder {
        var testName: String? = null
        var beforeTestSection: (() -> Unit)? = null
        var afterTestSection: (() -> Unit)? = null
        var mainTestSection: (TestContext.() -> Unit)? = null

        fun build(): TestBody {

            return TestBody(
                requireNotNull(testName),
                requireNotNull(beforeTestSection),
                requireNotNull(afterTestSection),
                requireNotNull(mainTestSection)
            )
        }
    }
}