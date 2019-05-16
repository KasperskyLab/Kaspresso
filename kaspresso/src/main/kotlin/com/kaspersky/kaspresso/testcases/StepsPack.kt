package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.core.TestContext

/**
 * A base class for all step packs. A representation of some repeating steps inside the [TestCase].
 */
abstract class StepsPack {

    /**
     * Steps to run. Need to be implemented in derived [StepsPack].
     */
    protected abstract val steps: TestContext.() -> Unit

    internal operator fun invoke(testContext: TestContext) = steps.invoke(testContext)
}