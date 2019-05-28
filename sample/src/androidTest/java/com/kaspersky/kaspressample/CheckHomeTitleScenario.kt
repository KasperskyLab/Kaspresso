package com.kaspersky.kaspressample

import com.kaspersky.kaspresso.testcases.Scenario
import com.kaspersky.kaspresso.testcases.core.TestContext

class CheckHomeTitleScenario : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {

        step("Check Home Title") {}

        step("Just Empty Step") {}
    }
}