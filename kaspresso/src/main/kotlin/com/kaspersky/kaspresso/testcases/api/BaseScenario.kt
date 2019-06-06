package com.kaspersky.kaspresso.testcases.api

import com.kaspersky.kaspresso.testcases.core.TestContext

/**
 * A base class for parametrized scenarios. A representation of some repeating steps inside the [TestCase].
 *
 *  @param ScenarioData test data created in testcase before section
 */
abstract class BaseScenario<ScenarioData> {

    /**
     * Steps to run. Need to be implemented in derived [Scenario].
     */
    protected abstract val steps: TestContext<ScenarioData>.() -> Unit

    internal operator fun invoke(testContext: TestContext<ScenarioData>) = steps.invoke(testContext)
}