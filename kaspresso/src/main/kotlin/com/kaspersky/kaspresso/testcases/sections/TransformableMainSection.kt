package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext

interface TransformableMainSection<BeforeSectionData, MainSectionData> {
    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) optional [InitialisableMainSection.initialisation]
     * 3) optional [transformation]s sections (only in case of [initialisation] was run before )
     * 4) [MainTestSection]'s steps
     * 5) [AfterTestSection]. [AfterTestSection] are invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<MainSectionData>.() -> Unit)

    /**
     * Invokes after [BeforeTestSection] and [initialisation] and before [MainTestSection].
     *
     * It's possible to add multiple transformation blocks
     *
     * @param steps steps to run.
     */
    fun transformation(steps: MainSectionData.() -> Unit): TransformableMainSection<BeforeSectionData, MainSectionData>
}