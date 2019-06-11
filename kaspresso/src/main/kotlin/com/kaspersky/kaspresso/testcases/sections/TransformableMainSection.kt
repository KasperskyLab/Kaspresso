package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.api.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext

interface TransformableMainSection<BeforeSectionData, MainSectionData> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) Optional [InitialisableMainSection.initialisation],
     * 3) Optional [transformation]'s sections (only if [InitialisableMainSection.initialisation] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<MainSectionData>.() -> Unit)

    /**
     * Invokes after [BeforeTestSection] and [InitialisableMainSection.initialisation] and before [MainTestSection].
     * It's possible to add multiple transformation blocks.
     *
     * @param steps steps to run.
     * @return [TransformableMainSection] to continue building a test.
     */
    fun transformation(
        steps: MainSectionData.() -> Unit
    ): TransformableMainSection<BeforeSectionData, MainSectionData>
}