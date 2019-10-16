package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

open class ParentTestCase(
    kaspressoBuilderAdditional: Kaspresso.Builder.() -> Unit
) : TestCase(kaspressoBuilder = Kaspresso.Builder.default().apply {
        beforeEachTest {
            testLogger.i("beforeTestFirstAction")
            DefaultActionsChecker.putBeforeFirst()
        }
        afterEachTest {
            testLogger.i("afterTestFirstAction")
            DefaultActionsChecker.putAfterFirst()
        }
        kaspressoBuilderAdditional.invoke(this)
    }
)