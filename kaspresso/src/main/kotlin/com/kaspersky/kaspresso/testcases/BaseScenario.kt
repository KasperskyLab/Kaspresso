package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.core.TestContext

/**
 * A base class for parametrized scenarios. A representation of some repeating steps inside the [TestCase].
 */
abstract class BaseScenario<MainSectionData> {

    /**
     * Steps to run. Need to be implemented in derived [Scenario].
     */
    protected abstract val steps: TestContext<MainSectionData>.() -> Unit

    internal operator fun invoke(testContext: TestContext<MainSectionData>) = steps.invoke(testContext)
}