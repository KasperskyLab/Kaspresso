package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.core.TestContext
import com.kaspersky.kaspresso.testcases.core.TestRunner

class MainTestSection<BeforeSectionData, MainSectionData>(
    private val builder: TestBody.Builder<BeforeSectionData, MainSectionData>
) {

    /**
     * Runs [beforeTestActions], [TestCase]'s [steps] and then runs [afterTestActions]. [afterTestActions] are invoked
     * even if [beforeTestActions] or [TestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    fun runSteps(steps: TestContext<MainSectionData>.() -> Unit) {
        val testBody = builder
            .apply { mainTestSection = steps }
            .build()
        TestRunner<BeforeSectionData, MainSectionData>().run(testBody)
    }
}