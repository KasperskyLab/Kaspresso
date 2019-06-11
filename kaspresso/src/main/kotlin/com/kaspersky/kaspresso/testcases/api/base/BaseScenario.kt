package com.kaspersky.kaspresso.testcases.api.base

import com.kaspersky.kaspresso.testcases.core.TestContext

/**
 * A base class for parametrized scenarios. A representation of some repeating steps inside the
 * [com.kaspersky.kaspresso.testcases.api.TestCase].
 *
 *  @param ScenarioData test data created in testcase's before section.
 */
abstract class BaseScenario<ScenarioData> {

    /**
     * Steps to run. Need to be implemented in derived [com.kaspersky.kaspresso.testcases.api.Scenario].
     */
    protected abstract val steps: TestContext<ScenarioData>.() -> Unit

    internal operator fun invoke(testContext: TestContext<ScenarioData>) = steps.invoke(testContext)
}