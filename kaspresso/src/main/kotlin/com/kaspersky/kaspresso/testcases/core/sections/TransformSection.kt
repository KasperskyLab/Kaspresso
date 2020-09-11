package com.kaspersky.kaspresso.testcases.core.sections

import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

interface TransformSection<Data> {

    /**
     * Runs:
     * 1) Optional [BeforeTestSection],
     * 2) Optional [InitSection.init],
     * 3) Optional [transform]'s sections (only if [InitSection.init] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: TestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [BeforeTestSection] and [InitSection.init] but before [MainTestSection].
     * It's possible to add multiple transform blocks.
     *
     * @param actions actions to run.
     * @return [TransformSection] to continue building a test.
     */
    fun transform(actions: Data.() -> Unit): TransformSection<Data>
}
