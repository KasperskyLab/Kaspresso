package com.kaspersky.kaspressample.scenarios

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class CheckHomeTitleScenario : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {

        step("Check Home Title") {}

        step("Just Empty Step") {}
    }
}