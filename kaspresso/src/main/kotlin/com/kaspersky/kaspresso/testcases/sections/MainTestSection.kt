package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext
import com.kaspersky.kaspresso.testcases.core.TestRunner
import com.kaspersky.kaspresso.testcases.models.TestBody

class MainTestSection<BeforeSectionData, MainSectionData>(
    private val builder: TestBody.Builder<BeforeSectionData, MainSectionData>
) : InitialisableMainSection<BeforeSectionData, MainSectionData>,
    TransformableMainSection<BeforeSectionData, MainSectionData> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) optional [initialisation]
     * 3) optional [transformation]s sections (only in case of [initialisation] was run before )
     * 4) [MainTestSection]'s steps
     * 5) [AfterTestSection]. [AfterTestSection] are invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    override fun run(steps: TestContext<MainSectionData>.() -> Unit) {
        val testBody = builder
            .apply { mainTestSection = steps }
            .build()
        TestRunner<BeforeSectionData, MainSectionData>().run(testBody)
    }

    /**
     * Invokes after [BeforeTestSection] and [initialisation] and before [MainTestSection].
     *
     * It's possible to add multiple transformation blocks
     *
     * @param steps steps to run.
     */
    override fun transformation(steps: MainSectionData.() -> Unit): TransformableMainSection<BeforeSectionData, MainSectionData> {
        builder.apply { conditionSectionsList.add(steps) }
        return this
    }

    /**
     * Invokes after [BeforeTestSection]. Running to init test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     */
    override fun initialisation(actions: BeforeSectionData.() -> Unit): TransformableMainSection<BeforeSectionData, MainSectionData> {
        builder.apply { initialisationSection = actions }
        return this
    }
}