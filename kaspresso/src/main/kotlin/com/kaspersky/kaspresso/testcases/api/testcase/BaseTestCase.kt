package com.kaspersky.kaspresso.testcases.api.testcase

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params
import com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection
import com.kaspersky.kaspresso.testcases.core.sections.MainTestSection
import com.kaspersky.kaspresso.testcases.core.sections.TransformSection
import com.kaspersky.kaspresso.testcases.core.testassistants.TestAssistantsProvider
import com.kaspersky.kaspresso.testcases.core.testassistants.TestAssistantsProviderImpl
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.TestBody

/**
 * The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of
 * [TestCase] as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use
 * [com.kaspersky.kaspresso.testcases.api.scenario.Scenario] instead.
 *
 * @param InitData data initialized in before section.
 * @param Data data transformed from [InitData] by special function.
 */
abstract class BaseTestCase<InitData, Data>(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.advanced(),
    private val dataProducer: (((InitData.() -> Unit)?) -> Data),
    private val mainSectionEnrichers: List<MainSectionEnricher<Data>> = emptyList()
) : TestAssistantsProvider {

    internal val kaspresso: Kaspresso = kaspressoBuilder.build()

    private val testCaseName = javaClass.simpleName
    private val testAssistantsProvider = TestAssistantsProviderImpl(kaspresso)

    override val adbServer: AdbServer = testAssistantsProvider.adbServer
    override val device: Device = testAssistantsProvider.device
    override val testLogger: UiTestLogger = testAssistantsProvider.testLogger
    override val params: Params = testAssistantsProvider.params

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
        val testBodyBuilder = createBaseTestBodyBuilder(testName)
        return BeforeTestSection(kaspresso, testBodyBuilder).beforeTest(actions)
    }

    /**
     * Starts the building of a test from data initialization section. Sets
     * [com.kaspersky.kaspresso.testcases.core.sections.InitSection] actions and returns an existing instance of
     * [MainTestSection] to continue building a tests.
     *
     * @param testName a name of the test.
     * @param actions actions to be wrapped and invoked before the test for data initialization
     * @return an existing instance of [MainTestSection].
     */
    protected fun init(
        testName: String = testCaseName,
        actions: InitData.() -> Unit
    ): TransformSection<Data> {
        val testBodyBuilder = createBaseTestBodyBuilder(testName).apply {
            initActions = actions
        }
        return MainTestSection(kaspresso, testBodyBuilder)
    }

    /**
     * Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection] steps, creates an instance of
     * [MainTestSection] and runs it
     *
     * @param testName a name of the test.
     * @param steps actions to invoke in main test section.
     */
    protected fun run(
        testName: String = testCaseName,
        steps: TestContext<Data>.() -> Unit
    ) {
        val testBodyBuilder = createBaseTestBodyBuilder(testName)
        MainTestSection(kaspresso, testBodyBuilder).run(steps)
    }

    private fun createBaseTestBodyBuilder(testName: String): TestBody.Builder<InitData, Data> {
        return TestBody.Builder<InitData, Data>().apply {
            this.testName = testName
            this.dataProducer = this@BaseTestCase.dataProducer
            this.mainSectionEnrichers = this@BaseTestCase.mainSectionEnrichers
        }
    }
}