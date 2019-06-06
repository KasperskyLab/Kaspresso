package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext


interface InitialisableMainSection<BeforeSectionData, MainSectionData> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) optional [initialisation]
     * 3) optional [TransformableMainSection.transformation]s sections (only in case of [initialisation] was run before )
     * 4) [MainTestSection]'s steps
     * 5) [AfterTestSection]. [AfterTestSection] are invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<MainSectionData>.() -> Unit)

    /**
     * Invokes after [BeforeTestSection]. Running to init test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     */
    fun initialisation(actions: BeforeSectionData.() -> Unit): TransformableMainSection<BeforeSectionData, MainSectionData>
}