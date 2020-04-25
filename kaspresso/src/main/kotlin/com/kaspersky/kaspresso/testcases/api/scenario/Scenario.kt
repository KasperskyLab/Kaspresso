package com.kaspersky.kaspresso.testcases.api.scenario

import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

/**
 * The base class for scenarios. A representation of some repeating steps inside the
 * [com.kaspersky.kaspresso.testcases.api.testcase.TestCase].
 */
abstract class Scenario : BaseScenario<Unit>() {

    abstract override val steps: TestContext<Unit>.() -> Unit
}
