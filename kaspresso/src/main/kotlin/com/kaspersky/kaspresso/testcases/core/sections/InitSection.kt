package com.kaspersky.kaspresso.testcases.core.sections

import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

interface InitSection<InitData, Data> {

    /**
     * Runs:
     * 1) Optional [BeforeTestSection],
     * 2) Optional [init],
     * 3) Optional [TransformSection.transform]'s sections (only if [init] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [BeforeTestSection]. Running to init test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [TransformSection] to continue building a test.
     */
    fun init(actions: InitData.() -> Unit): TransformSection<Data>
}
