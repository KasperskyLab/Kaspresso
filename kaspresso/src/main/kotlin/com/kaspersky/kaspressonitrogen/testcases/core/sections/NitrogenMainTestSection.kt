package com.kaspersky.kaspressonitrogen.testcases.core.sections

import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase
import com.kaspersky.kaspressonitrogen.testcases.core.NitrogenTestRunner
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext
import com.kaspersky.kaspressonitrogen.testcases.models.NitrogenTestBody

class NitrogenMainTestSection<InitData, Data> internal constructor(
    private val kaspresso: KaspressoNitrogen,
    private val testBodyBuilder: NitrogenTestBody.Builder<InitData, Data>
) : NitrogenInitSection<InitData, Data>, NitrogenTransformSection<Data> {

    /**
     * Runs:
     * 1) Optional [NitrogenBeforeTestSection],
     * 2) Optional [init],
     * 3) Optional [transform]'s sections (only if [init] was called before),
     * 4) [NitrogenMainTestSection]'s steps,
     * 5) [NitrogenAfterTestSection]. [NitrogenAfterTestSection] is invoked even if [NitrogenBeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    override fun run(steps: NitrogenTestContext<Data>.() -> Unit) {
        val testBody = testBodyBuilder.apply { this.steps = steps }.build()
        NitrogenTestRunner<InitData, Data>(
            kaspresso
        ).run(testBody)
    }

    /**
     * Can be invoked after [NitrogenBeforeTestSection]. Running to init test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [NitrogenTransformSection] to continue building a test.
     */
    override fun init(actions: InitData.() -> Unit): NitrogenTransformSection<Data> {
        testBodyBuilder.apply { initActions = actions }
        return this
    }

    /**
     * Can be invoked after [NitrogenBeforeTestSection] and [init] but before [NitrogenMainTestSection].
     *
     * It's possible to add multiple transform blocks.
     *
     * @param actions actions to run.
     * @return [NitrogenTransformSection] to continue building a test.
     */
    override fun transform(actions: Data.() -> Unit): NitrogenTransformSection<Data> {
        testBodyBuilder.apply { transformActionsList.add(actions) }
        return this
    }
}
