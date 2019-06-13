package com.kaspersky.kaspressample.scenarios

import com.kaspersky.kaspresso.testcases.api.BaseScenario
import com.kaspersky.kaspresso.testcases.core.TestContext

class CheckHomeTitleNoParametersScenario<T> : BaseScenario<T>() {

    override val steps: TestContext<T>.() -> Unit = {

        step("Check Home Title") {}

        step("Just Empty Step") {}
    }
}