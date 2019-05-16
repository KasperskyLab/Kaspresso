package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.core.StepContext
import com.kaspersky.kaspresso.testcases.core.TestBody

class MainTestSection(
    private val builder: TestBody.Builder
) : TestBody.Runner {

    /**
     * Runs [beforeTestActions], [TestCase]'s [steps] and then runs [afterTestActions]. [afterTestActions] are invoked
     * even if [beforeTestActions] or [TestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    fun runSteps(steps: StepContext.() -> Unit) {
        runTest(
            builder
                .apply { mainTestSection = steps }
                .build()
        )
    }
}