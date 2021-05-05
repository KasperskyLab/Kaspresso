package com.kaspersky.kaspressonitrogen.testcases.core.sections

import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext
import com.kaspersky.kaspressonitrogen.testcases.api.testcase.NitrogenBaseTestCase

interface NitrogenTransformSection<Data> {

    /**
     * Runs:
     * 1) Optional [NitrogenBeforeTestSection],
     * 2) Optional [NitrogenInitSection.init],
     * 3) Optional [transform]'s sections (only if [NitrogenInitSection.init] was called before),
     * 4) [NitrogenMainTestSection]'s steps,
     * 5) [NitrogenAfterTestSection]. [NitrogenAfterTestSection] is invoked even if [NitrogenBeforeTestSection] or [NitrogenBaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    fun run(steps: NitrogenTestContext<Data>.() -> Unit)

    /**
     * Can be invoked after [NitrogenBeforeTestSection] and [NitrogenInitSection.init] but before [NitrogenMainTestSection].
     * It's possible to add multiple transform blocks.
     *
     * @param actions actions to run.
     * @return [NitrogenTransformSection] to continue building a test.
     */
    fun transform(actions: Data.() -> Unit): NitrogenTransformSection<Data>
}
