package com.kaspersky.kaspressample

import com.kaspersky.kaspresso.testcases.BaseScenario
import com.kaspersky.kaspresso.testcases.Scenario
import com.kaspersky.kaspresso.testcases.core.TestContext

class CheckHomeTitleNoParametersScenario<T> : BaseScenario<T>() {

    override val steps: TestContext<T>.() -> Unit = {

        step("Check Home Title") {}

        step("Just Empty Step") {}
    }
}