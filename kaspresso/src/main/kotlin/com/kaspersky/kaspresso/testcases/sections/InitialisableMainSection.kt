package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.api.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext

interface InitialisableMainSection<BeforeSectionData, MainSectionData> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) Optional [initialisation],
     * 3) Optional [TransformableMainSection.transformation]'s sections (only if [initialisation] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<MainSectionData>.() -> Unit)

    /**
     * Invokes after [BeforeTestSection]. Running to init test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [TransformableMainSection] to continue building a test.
     */
    fun initialisation(
        actions: BeforeSectionData.() -> Unit
    ): TransformableMainSection<BeforeSectionData, MainSectionData>
}