package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.api.base.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext

interface TransformDataSection<Data> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) Optional [InitDataSection.initData],
     * 3) Optional [transformData]'s sections (only if [InitDataSection.initData] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [BeforeTestSection] and [InitDataSection.initData] but before [MainTestSection].
     * It's possible to add multiple transformData blocks.
     *
     * @param actions actions to run.
     * @return [TransformDataSection] to continue building a test.
     */
    fun transformData(
        actions: Data.() -> Unit
    ): TransformDataSection<Data>
}