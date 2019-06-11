package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.api.base.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext

interface InitDataSection<InitData, Data> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) Optional [initData],
     * 3) Optional [TransformDataSection.transformData]'s sections (only if [initData] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [BeforeTestSection]. Running to initData test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [TransformDataSection] to continue building a test.
     */
    fun initData(
        actions: InitData.() -> Unit
    ): TransformDataSection<Data>
}