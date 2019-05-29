package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.core.TestContext
import com.kaspersky.kaspresso.testcases.core.TestRunner
import com.kaspersky.kaspresso.testcases.models.TestBody

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

    /**
     * Invokes after [BeforeTestSection] and before [MainTestSection].
     *
     * It's possible to add multiple conditions blocks
     *
     * @param steps steps to run.
     */
    fun conditions(steps: MainSectionData.() -> Unit): MainTestSection<BeforeSectionData, MainSectionData> {
        builder.apply { conditionSectionsList.add(steps) }
        return this
    }
}