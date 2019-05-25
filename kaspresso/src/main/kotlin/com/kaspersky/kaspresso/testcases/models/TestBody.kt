package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.core.TestContext

class TestBody(
    val testInfo: InternalTestInfo,
    val beforeTestActions: () -> Unit,
    val afterTestActions: () -> Unit,
    val mainSection: TestContext.() -> Unit
) {
    class Builder {
        var testResult: InternalTestInfo? = null
        var beforeTestSection: (() -> Unit)? = null
        var afterTestSection: (() -> Unit)? = null
        var mainTestSection: (TestContext.() -> Unit)? = null

        fun build(): TestBody {

            return TestBody(
                requireNotNull(testResult),
                requireNotNull(beforeTestSection),
                requireNotNull(afterTestSection),
                requireNotNull(mainTestSection)
            )
        }
    }
}