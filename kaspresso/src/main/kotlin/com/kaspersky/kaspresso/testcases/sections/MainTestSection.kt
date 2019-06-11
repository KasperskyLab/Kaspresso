package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.api.base.BaseTestCase
import com.kaspersky.kaspresso.testcases.core.TestContext
import com.kaspersky.kaspresso.testcases.core.TestRunner
import com.kaspersky.kaspresso.testcases.models.TestBody

class MainTestSection<InitData, Data> internal constructor(
    private val configurator: Configurator,
    private val testBodyBuilder: TestBody.Builder<InitData, Data>
) : InitDataSection<InitData, Data>, TransformDataSection<Data> {

    /**
     * Runs:
     * 1) [BeforeTestSection],
     * 2) Optional [initData],
     * 3) Optional [transformData]'s sections (only if [initData] was called before),
     * 4) [MainTestSection]'s steps,
     * 5) [AfterTestSection]. [AfterTestSection] is invoked even if [BeforeTestSection] or [BaseTestCase]'s [steps] failed.
     *
     * @param steps steps to run.
     */
    override fun run(
        steps: TestContext<Data>.() -> Unit
    ) {
        val testBody = testBodyBuilder.apply { this.steps = steps }.build()

        TestRunner<InitData, Data>(configurator).run(testBody)
    }

    /**
     * Can be invoked after [BeforeTestSection]. Running to initData test data using dsl.
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [TransformDataSection] to continue building a test.
     */
    override fun initData(
        actions: InitData.() -> Unit
    ): TransformDataSection<Data> {

        testBodyBuilder.apply { initDataActions = actions }
        return this
    }

    /**
     * Can be invoked after [BeforeTestSection] and [initData] but before [MainTestSection].
     *
     * It's possible to add multiple transformData blocks.
     *
     * @param actions actions to run.
     * @return [TransformDataSection] to continue building a test.
     */
    override fun transformData(
        actions: Data.() -> Unit
    ): TransformDataSection<Data> {

        testBodyBuilder.apply { transformDataActionsList.add(actions) }
        return this
    }
}