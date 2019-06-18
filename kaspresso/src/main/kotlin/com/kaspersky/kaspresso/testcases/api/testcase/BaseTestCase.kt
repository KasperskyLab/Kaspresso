package com.kaspersky.kaspresso.testcases.api.testcase

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection

/**
 *  A base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of
 *  [TestCase] as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may
 *  produce an exception caused by re-initialization of the [Configurator], use
 *  [com.kaspersky.kaspresso.testcases.api.scenario.Scenario] instead.
 *
 *  @param InitData data initialized in before section.
 *  @param Data data transformed from [InitData] by special function.
 */
abstract class BaseTestCase<InitData, Data>(
    configBuilder: Configurator.Builder = Configurator.Builder.default(),
    private val dataProducer: (((InitData.() -> Unit)?) -> Data)
) {
    private val testCaseName = javaClass.simpleName

    internal val configurator: Configurator = configBuilder.build()

    /**
     * Starts the building of a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue the building a test.
     *
     * @param testName a name of the test.
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    protected fun before(
        testName: String = testCaseName,
        actions: BaseTestContext.() -> Unit
    ): AfterTestSection<InitData, Data> {

        val testBodyBuilder = TestBody.Builder<InitData, Data>().apply {
            this.testName = testName
            this.dataProducer = this@BaseTestCase.dataProducer
        }

        return BeforeTestSection(configurator, testBodyBuilder).beforeTest(actions)
    }
}