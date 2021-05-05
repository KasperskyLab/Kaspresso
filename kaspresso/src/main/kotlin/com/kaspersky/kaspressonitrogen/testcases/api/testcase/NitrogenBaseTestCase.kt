package com.kaspersky.kaspressonitrogen.testcases.api.testcase

import com.kaspersky.kaspressonitrogen.enricher.NitrogenMainSectionEnricher
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Params
import com.kaspersky.kaspressonitrogen.testcases.core.testassistants.NitrogenTestAssistantsProvider
import com.kaspersky.kaspresso.testcases.core.testassistants.NitrogenTestAssistantsProviderImpl
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext
import com.kaspersky.kaspressonitrogen.testcases.models.NitrogenTestBody
import com.kaspersky.kaspressonitrogen.testcases.core.sections.NitrogenAfterTestSection
import com.kaspersky.kaspressonitrogen.testcases.core.sections.NitrogenBeforeTestSection
import com.kaspersky.kaspressonitrogen.testcases.core.sections.NitrogenMainTestSection
import com.kaspersky.kaspressonitrogen.testcases.core.sections.NitrogenTransformSection

abstract class NitrogenBaseTestCase<InitData, Data>(
    kaspressoBuilder: KaspressoNitrogen.Builder = KaspressoNitrogen.Builder.simple(),
    private val dataProducer: (((InitData.() -> Unit)?) -> Data),
    private val mainSectionEnrichers: List<NitrogenMainSectionEnricher<Data>> = emptyList()
) : NitrogenTestAssistantsProvider {

    internal val kaspresso: KaspressoNitrogen = kaspressoBuilder.build()

    private val testCaseName = javaClass.simpleName
    private val testAssistantsProvider = NitrogenTestAssistantsProviderImpl(kaspresso)

    override val testLogger: UiTestLogger = testAssistantsProvider.testLogger
    override val params: Params = testAssistantsProvider.params

    /**
     * Starts the building of a test, sets the [NitrogenBeforeTestSection] actions and returns an existing instance of
     * [NitrogenAfterTestSection] to continue the building a test.
     *
     * @param testName a name of the test.
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [NitrogenAfterTestSection].
     */
    protected fun before(
        testName: String = testCaseName,
        actions: NitrogenBaseTestContext.() -> Unit
    ): NitrogenAfterTestSection<InitData, Data> {
        val testBodyBuilder = createBaseTestBodyBuilder(testName)
        return NitrogenBeforeTestSection(kaspresso, testBodyBuilder).beforeTest(actions)
    }

    /**
     * Starts the building of a test from data initialization section. Sets
     * [com.kaspersky.kaspressonitrogen.testcases.core.sections.NitrogenInitSection] actions and returns an existing instance of
     * [NitrogenMainTestSection] to continue building a tests.
     *
     * @param testName a name of the test.
     * @param actions actions to be wrapped and invoked before the test for data initialization
     * @return an existing instance of [NitrogenMainTestSection].
     */
    protected fun init(
        testName: String = testCaseName,
        actions: InitData.() -> Unit
    ): NitrogenTransformSection<Data> {
        val testBodyBuilder = createBaseTestBodyBuilder(testName).apply {
            initActions = actions
        }
        return NitrogenMainTestSection(kaspresso, testBodyBuilder)
    }

    /**
     * Sets [com.kaspersky.kaspressonitrogen.testcases.core.sections.NitrogenMainTestSection] steps, creates an instance of
     * [NitrogenMainTestSection] and runs it
     *
     * @param testName a name of the test.
     * @param steps actions to invoke in main test section.
     */
    protected fun run(
        testName: String = testCaseName,
        steps: NitrogenTestContext<Data>.() -> Unit
    ) {
        val testBodyBuilder = createBaseTestBodyBuilder(testName)
        NitrogenMainTestSection(kaspresso, testBodyBuilder).run(steps)
    }

    private fun createBaseTestBodyBuilder(testName: String): NitrogenTestBody.Builder<InitData, Data> {
        return NitrogenTestBody.Builder<InitData, Data>().apply {
            this.testName = testName
            this.dataProducer = this@NitrogenBaseTestCase.dataProducer
            this.mainSectionEnrichers = this@NitrogenBaseTestCase.mainSectionEnrichers
        }
    }
}

