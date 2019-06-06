package com.kaspersky.kaspresso.testcases.api

import com.kaspersky.kaspresso.testcases.core.TestContext

/**
 * A base class for scenarios. A representation of some repeating steps inside the [TestCase].
 */
abstract class Scenario : BaseScenario<Unit>(){

    override  abstract val steps: TestContext<Unit>.() -> Unit
}