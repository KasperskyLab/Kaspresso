package com.kaspersky.kaspressample

import com.kaspersky.kaspresso.testcases.api.base.BaseScenario
import com.kaspersky.kaspresso.testcases.core.TestContext

class CheckHomeTitleNoParametersScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {

        step("Check Home Title") {}

        step("Just Empty Step") {}
    }
}