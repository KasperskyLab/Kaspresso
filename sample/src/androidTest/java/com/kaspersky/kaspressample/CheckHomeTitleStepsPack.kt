package com.kaspersky.kaspressample

import com.kaspersky.kaspresso.testcases.StepsPack
import com.kaspersky.kaspresso.testcases.core.TestContext

class CheckHomeTitleStepsPack : StepsPack() {

    override val steps: TestContext.() -> Unit = {

        step("Check Home Title StepsPack") {}

        step("Just Empty Step in StepsPack") {}
    }
}