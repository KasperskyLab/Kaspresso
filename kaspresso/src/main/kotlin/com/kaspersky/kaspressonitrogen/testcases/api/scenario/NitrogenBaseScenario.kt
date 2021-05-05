package com.kaspersky.kaspressonitrogen.testcases.api.scenario

import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext

abstract class NitrogenBaseScenario<ScenarioData> {

    /**
     * Steps to run. Need to be implemented in derived [Scenario].
     */
    protected abstract val steps: NitrogenTestContext<ScenarioData>.() -> Unit

    internal operator fun invoke(testContext: NitrogenTestContext<ScenarioData>) = steps.invoke(testContext)
}
