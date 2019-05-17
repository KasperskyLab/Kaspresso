package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.core.TestContext

/**
 * A base class for scenarios. A representation of some repeating steps inside the [TestCase].
 */
abstract class Scenario {

    /**
     * Steps to run. Need to be implemented in derived [Scenario].
     */
    protected abstract val steps: TestContext.() -> Unit

    internal operator fun invoke(testContext: TestContext) = steps.invoke(testContext)
}