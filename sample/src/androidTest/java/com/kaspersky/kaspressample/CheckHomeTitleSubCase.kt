package com.kaspersky.kaspressample

import com.kaspersky.kaspresso.testcases.SubCase
import com.kaspersky.kaspresso.testcases.core.StepContext

class CheckHomeTitleSubCase : SubCase() {

    override val steps: StepContext.() -> Unit = {
        step("Check Home Title SubCase") {}
    }

}