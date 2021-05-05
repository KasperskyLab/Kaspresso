package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

open class ParentTestCase(
    kaspressoBuilderAdditional: Kaspresso.Builder.() -> Unit
) : TestCase(kaspressoBuilder = Kaspresso.Builder.simple().apply {
        beforeEachTest {
            testLogger.i("beforeTestFirstAction")
            DefaultActionsChecker.putBeforeInParentTestCase()
        }
        afterEachTest {
            testLogger.i("afterTestFirstAction")
            DefaultActionsChecker.putAfterInParentTestCase()
        }
        kaspressoBuilderAdditional.invoke(this)
    }
)
