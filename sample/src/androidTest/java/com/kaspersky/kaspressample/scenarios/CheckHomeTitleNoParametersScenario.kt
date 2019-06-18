package com.kaspersky.kaspressample.scenarios

import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class CheckHomeTitleNoParametersScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {

        step("Check Home Title") {}

        step("Just Empty Step") {}
    }
}