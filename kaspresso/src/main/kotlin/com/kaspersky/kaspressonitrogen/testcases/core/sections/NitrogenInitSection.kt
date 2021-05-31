package com.kaspersky.kaspressonitrogen.testcases.core.sections

import com.kaspersky.kaspressonitrogen.testcases.api.testcase.NitrogenBaseTestCase
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext

interface NitrogenInitSection<InitData, Data> {

    /**
     * Runs:
     * 1) Optional [NitrogenBeforeTestSection],
     * 2) Optional [init],
     * 3) Optional [NitrogenTransformSection.transform]'s sections (only if [init] was called before),
     * 4) [NitrogenMainTestSection]'s steps,
     * 5) [NitrogenAfterTestSection]. [NitrogenAfterTestSection] is invoked even if [NitrogenBeforeTestSection] or [NitrogenBaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: NitrogenTestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [NitrogenBeforeTestSection]. Running to init test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [NitrogenTransformSection] to continue building a test.
     */
    fun init(actions: InitData.() -> Unit): NitrogenTransformSection<Data>
}
