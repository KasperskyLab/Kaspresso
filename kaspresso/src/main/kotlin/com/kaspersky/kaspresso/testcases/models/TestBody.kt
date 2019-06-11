package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.TestContext

internal class TestBody<InitData, Data>(
    val testName: String,
    val beforeTestActions: BaseTestContext.() -> Unit,
    val afterTestActions: BaseTestContext.() -> Unit,
    val steps: TestContext<Data>.() -> Unit,
    var initDataActions: (InitData.() -> Unit)?,
    val transformDataActionsList: List<Data.() -> Unit>,
    val dataProducer: (((InitData.() -> Unit)?) -> Data)
) {
    internal class Builder<InitData, Data> {
        var testName: String? = null
        var beforeTestActions: (BaseTestContext.() -> Unit)? = null
        var afterTestActions: (BaseTestContext.() -> Unit)? = null
        var steps: (TestContext<Data>.() -> Unit)? = null
        var initDataActions: (InitData.() -> Unit)? = null
        val transformDataActionsList: MutableList<Data.() -> Unit> = mutableListOf()
        var dataProducer: (((InitData.() -> Unit)?) -> Data)? = null

        fun build(): TestBody<InitData, Data> {
            checkInitializationInvariants()

            return TestBody(
                requireNotNull(testName),
                requireNotNull(beforeTestActions),
                requireNotNull(afterTestActions),
                requireNotNull(steps),
                initDataActions,
                transformDataActionsList,
                requireNotNull(dataProducer)
            )
        }

        private fun checkInitializationInvariants() {
            if (transformDataActionsList.isNotEmpty() && initDataActions == null) {
                throw IllegalArgumentException("Init data section can not be empty for non empty transform data section.")
            }
        }
    }
}